package com.ossrep.servicepoint.ingestion;

import java.io.InputStream;
import java.time.Instant;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import com.ossrep.servicepoint.repository.ServicePointEntity;
import com.ossrep.servicepoint.tdsp.TdspRepository;

import io.quarkus.logging.Log;

@ApplicationScoped
public class IngestionService {

    private static final long ADVISORY_LOCK_ID = 7_248_913_501L;

    private static final String UPSERT_SQL = """
            INSERT INTO service_point (tdsp_id, esiid, street, street_line2, city, state, zip, county,
                meter_read_cycle, status, premise_type, station_code,
                station_name, metered, open_service_orders, polr_customer_class,
                settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator,
                metered_service_type, metered_service_type_desc, created_at, updated_at)
            VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16,
                ?17, ?18, ?19, ?20, ?21, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            ON CONFLICT (esiid) DO UPDATE SET
                tdsp_id = EXCLUDED.tdsp_id,
                street = EXCLUDED.street,
                street_line2 = EXCLUDED.street_line2,
                city = EXCLUDED.city,
                state = EXCLUDED.state,
                zip = EXCLUDED.zip,
                county = EXCLUDED.county,
                meter_read_cycle = EXCLUDED.meter_read_cycle,
                status = EXCLUDED.status,
                premise_type = EXCLUDED.premise_type,
                station_code = EXCLUDED.station_code,
                station_name = EXCLUDED.station_name,
                metered = EXCLUDED.metered,
                open_service_orders = EXCLUDED.open_service_orders,
                polr_customer_class = EXCLUDED.polr_customer_class,
                settlement_ams_indicator = EXCLUDED.settlement_ams_indicator,
                tdsp_ams_indicator = EXCLUDED.tdsp_ams_indicator,
                switch_hold_indicator = EXCLUDED.switch_hold_indicator,
                metered_service_type = EXCLUDED.metered_service_type,
                metered_service_type_desc = EXCLUDED.metered_service_type_desc,
                updated_at = CURRENT_TIMESTAMP
            """;

    @Inject
    EntityManager entityManager;

    @Inject
    ErcotApiClient ercotApiClient;

    @Inject
    EsiidCsvParser csvParser;

    @Inject
    IngestionLogRepository ingestionLogRepository;

    @Inject
    TdspRepository tdspRepository;

    public void processArchives() {
        if (!tryAcquireLock()) {
            Log.info("Another instance is already running ERCOT ingestion, skipping");
            return;
        }

        try {
            Log.info("Starting ERCOT ESIID ingestion");

            String accessToken = ercotApiClient.authenticate();
            List<ErcotApiClient.ArchiveEntry> archives = ercotApiClient.listArchives(accessToken);

            List<ErcotApiClient.ArchiveEntry> unprocessed = archives.stream()
                    .filter(a -> !ingestionLogRepository.existsByFileName(a.fileName()))
                    .toList();

            Log.infof("Found %d unprocessed archives out of %d total", unprocessed.size(), archives.size());

            for (ErcotApiClient.ArchiveEntry archive : unprocessed) {
                processArchive(archive, accessToken);
            }

            Log.info("ERCOT ESIID ingestion complete");
        } finally {
            releaseLock();
        }
    }

    @Transactional
    boolean tryAcquireLock() {
        Object result = entityManager
                .createNativeQuery("SELECT pg_try_advisory_lock(:lockId)")
                .setParameter("lockId", ADVISORY_LOCK_ID)
                .getSingleResult();
        return Boolean.TRUE.equals(result);
    }

    @Transactional
    void releaseLock() {
        entityManager
                .createNativeQuery("SELECT pg_advisory_unlock(:lockId)")
                .setParameter("lockId", ADVISORY_LOCK_ID)
                .getSingleResult();
    }

    private void processArchive(ErcotApiClient.ArchiveEntry archive, String accessToken) {
        Log.infof("Processing archive: %s (type=%s, tdsp=%s)", archive.fileName(), archive.fileType(), archive.tdsp());

        Long tdspId = resolveTdspId(archive.tdsp());
        if (tdspId == null) {
            Log.warnf("Unknown TDSP code '%s' in archive %s, skipping", archive.tdsp(), archive.fileName());
            return;
        }

        IngestionLogEntity logEntry = createLogEntry(archive, tdspId);

        try (InputStream stream = ercotApiClient.downloadArchive(archive.downloadUrl(), accessToken)) {
            long recordCount = csvParser.parseZipStream(stream, batch -> upsertBatch(batch, tdspId));

            completeLogEntry(logEntry, recordCount);
            Log.infof("Completed %s: %d records", archive.fileName(), recordCount);
        } catch (Exception e) {
            failLogEntry(logEntry, e.getMessage());
            Log.errorf(e, "Failed to process archive: %s", archive.fileName());
        }
    }

    private Long resolveTdspId(String tdspCode) {
        return tdspRepository.findByCode(tdspCode)
                .map(t -> t.tdspId)
                .orElse(null);
    }

    @Transactional
    public void upsertBatch(List<ServicePointEntity> batch, Long tdspId) {
        for (ServicePointEntity entity : batch) {
            Long resolvedTdspId = tdspId;
            if (entity.tdspId != null) {
                resolvedTdspId = entity.tdspId;
            }

            entityManager.createNativeQuery(UPSERT_SQL)
                    .setParameter(1, resolvedTdspId)
                    .setParameter(2, entity.esiid)
                    .setParameter(3, entity.street)
                    .setParameter(4, entity.streetLine2)
                    .setParameter(5, entity.city)
                    .setParameter(6, entity.state)
                    .setParameter(7, entity.zip)
                    .setParameter(8, entity.county)
                    .setParameter(9, entity.meterReadCycle)
                    .setParameter(10, entity.status)
                    .setParameter(11, entity.premiseType)
                    .setParameter(12, entity.stationCode)
                    .setParameter(13, entity.stationName)
                    .setParameter(14, entity.metered)
                    .setParameter(15, entity.openServiceOrders)
                    .setParameter(16, entity.polrCustomerClass)
                    .setParameter(17, entity.settlementAmsIndicator)
                    .setParameter(18, entity.tdspAmsIndicator)
                    .setParameter(19, entity.switchHoldIndicator)
                    .setParameter(20, entity.meteredServiceType)
                    .setParameter(21, entity.meteredServiceTypeDesc)
                    .executeUpdate();
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    IngestionLogEntity createLogEntry(ErcotApiClient.ArchiveEntry archive, Long tdspId) {
        IngestionLogEntity log = new IngestionLogEntity();
        log.tdspId = tdspId;
        log.fileName = archive.fileName();
        log.fileType = archive.fileType();
        log.status = "PROCESSING";
        log.startedAt = Instant.now();
        ingestionLogRepository.persist(log);
        return log;
    }

    @Transactional
    void completeLogEntry(IngestionLogEntity log, long recordCount) {
        log.recordCount = recordCount;
        log.status = "COMPLETED";
        log.completedAt = Instant.now();
        ingestionLogRepository.persist(log);
    }

    @Transactional
    void failLogEntry(IngestionLogEntity log, String errorMessage) {
        log.status = "FAILED";
        log.completedAt = Instant.now();
        log.errorMessage = errorMessage;
        ingestionLogRepository.persist(log);
    }
}
