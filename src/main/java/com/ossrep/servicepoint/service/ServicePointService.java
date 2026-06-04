package com.ossrep.servicepoint.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.ossrep.servicepoint.repository.ServicePointEntity;
import com.ossrep.servicepoint.repository.ServicePointRepository;

@ApplicationScoped
public class ServicePointService {

    private static final int BATCH_FLUSH_SIZE = 500;

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
    ServicePointRepository repository;

    @Inject
    EntityManager entityManager;

    @Inject
    ServicePointEventEmitter eventEmitter;

    public List<ServicePoint> listAll() {
        return repository.listAll().stream()
                .map(this::toDomain)
                .toList();
    }

    public List<ServicePoint> listPaged(int page, int size) {
        return repository.findAll()
                .page(page, size)
                .list()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    public long count() {
        return repository.count();
    }

    public Optional<ServicePoint> findById(Long servicePointId) {
        return repository.findByIdOptional(servicePointId)
                .map(this::toDomain);
    }

    @Transactional
    public ServicePoint create(@Valid ServicePoint servicePoint) {
        ServicePointEntity entity = toEntity(servicePoint);
        entity.createdAt = Instant.now();
        entity.updatedAt = entity.createdAt;
        repository.persist(entity);

        ServicePoint created = toDomain(entity);
        eventEmitter.emit(new ServicePointEvent(
                ServicePointEvent.EventType.SERVICE_POINT_CREATED,
                Instant.now(),
                created
        ));
        return created;
    }

    @Transactional
    public Optional<ServicePoint> update(Long servicePointId, @Valid ServicePoint servicePoint) {
        return repository.findByIdOptional(servicePointId)
                .map(entity -> {
                    entity.tdspId = servicePoint.tdspId();
                    entity.esiid = servicePoint.esiid();
                    entity.street = servicePoint.street();
                    entity.streetLine2 = servicePoint.streetLine2();
                    entity.city = servicePoint.city();
                    entity.state = servicePoint.state();
                    entity.zip = servicePoint.zip();
                    entity.county = servicePoint.county();
                    entity.meterReadCycle = servicePoint.meterReadCycle();
                    entity.status = servicePoint.status();
                    entity.premiseType = servicePoint.premiseType();
                    entity.stationCode = servicePoint.stationCode();
                    entity.stationName = servicePoint.stationName();
                    entity.metered = servicePoint.metered();
                    entity.openServiceOrders = servicePoint.openServiceOrders();
                    entity.polrCustomerClass = servicePoint.polrCustomerClass();
                    entity.settlementAmsIndicator = servicePoint.settlementAmsIndicator();
                    entity.tdspAmsIndicator = servicePoint.tdspAmsIndicator();
                    entity.switchHoldIndicator = servicePoint.switchHoldIndicator();
                    entity.meteredServiceType = servicePoint.meteredServiceType();
                    entity.meteredServiceTypeDesc = servicePoint.meteredServiceTypeDesc();
                    entity.updatedAt = Instant.now();

                    ServicePoint updated = toDomain(entity);
                    eventEmitter.emit(new ServicePointEvent(
                            ServicePointEvent.EventType.SERVICE_POINT_UPDATED,
                            Instant.now(),
                            updated
                    ));
                    return updated;
                });
    }

    @Transactional
    public boolean delete(Long servicePointId) {
        return repository.findByIdOptional(servicePointId)
                .map(entity -> {
                    ServicePoint deleted = toDomain(entity);
                    repository.delete(entity);
                    eventEmitter.emit(new ServicePointEvent(
                            ServicePointEvent.EventType.SERVICE_POINT_DELETED,
                            Instant.now(),
                            deleted
                    ));
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public long bulkUpsert(List<ServicePoint> servicePoints) {
        long count = 0;
        for (ServicePoint sp : servicePoints) {
            entityManager.createNativeQuery(UPSERT_SQL)
                    .setParameter(1, sp.tdspId())
                    .setParameter(2, sp.esiid())
                    .setParameter(3, sp.street())
                    .setParameter(4, sp.streetLine2())
                    .setParameter(5, sp.city())
                    .setParameter(6, sp.state())
                    .setParameter(7, sp.zip())
                    .setParameter(8, sp.county())
                    .setParameter(9, sp.meterReadCycle())
                    .setParameter(10, sp.status())
                    .setParameter(11, sp.premiseType())
                    .setParameter(12, sp.stationCode())
                    .setParameter(13, sp.stationName())
                    .setParameter(14, sp.metered())
                    .setParameter(15, sp.openServiceOrders())
                    .setParameter(16, sp.polrCustomerClass())
                    .setParameter(17, sp.settlementAmsIndicator())
                    .setParameter(18, sp.tdspAmsIndicator())
                    .setParameter(19, sp.switchHoldIndicator())
                    .setParameter(20, sp.meteredServiceType())
                    .setParameter(21, sp.meteredServiceTypeDesc())
                    .executeUpdate();
            count++;
            if (count % BATCH_FLUSH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
        return count;
    }

    private ServicePoint toDomain(ServicePointEntity entity) {
        return new ServicePoint(
                entity.servicePointId,
                entity.tdspId,
                entity.esiid,
                entity.street,
                entity.streetLine2,
                entity.city,
                entity.state,
                entity.zip,
                entity.county,
                entity.meterReadCycle,
                entity.status,
                entity.premiseType,
                entity.stationCode,
                entity.stationName,
                entity.metered,
                entity.openServiceOrders,
                entity.polrCustomerClass,
                entity.settlementAmsIndicator,
                entity.tdspAmsIndicator,
                entity.switchHoldIndicator,
                entity.meteredServiceType,
                entity.meteredServiceTypeDesc,
                entity.createdAt,
                entity.updatedAt
        );
    }

    private ServicePointEntity toEntity(ServicePoint domain) {
        ServicePointEntity entity = new ServicePointEntity();
        entity.tdspId = domain.tdspId();
        entity.esiid = domain.esiid();
        entity.street = domain.street();
        entity.streetLine2 = domain.streetLine2();
        entity.city = domain.city();
        entity.state = domain.state();
        entity.zip = domain.zip();
        entity.county = domain.county();
        entity.meterReadCycle = domain.meterReadCycle();
        entity.status = domain.status();
        entity.premiseType = domain.premiseType();
        entity.stationCode = domain.stationCode();
        entity.stationName = domain.stationName();
        entity.metered = domain.metered();
        entity.openServiceOrders = domain.openServiceOrders();
        entity.polrCustomerClass = domain.polrCustomerClass();
        entity.settlementAmsIndicator = domain.settlementAmsIndicator();
        entity.tdspAmsIndicator = domain.tdspAmsIndicator();
        entity.switchHoldIndicator = domain.switchHoldIndicator();
        entity.meteredServiceType = domain.meteredServiceType();
        entity.meteredServiceTypeDesc = domain.meteredServiceTypeDesc();
        return entity;
    }
}
