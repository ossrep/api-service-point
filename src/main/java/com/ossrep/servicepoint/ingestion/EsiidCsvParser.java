package com.ossrep.servicepoint.ingestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.enterprise.context.ApplicationScoped;

import com.ossrep.servicepoint.repository.ServicePointEntity;

import io.quarkus.logging.Log;

@ApplicationScoped
public class EsiidCsvParser {

    private static final int EXPECTED_COLUMNS = 22;
    private static final int BATCH_SIZE = 5000;

    public long parseZipStream(InputStream zipStream, Consumer<List<ServicePointEntity>> batchConsumer) throws IOException {
        long totalRecords = 0;

        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().toLowerCase().endsWith(".csv")) {
                    Log.infof("Parsing CSV entry: %s", entry.getName());
                    totalRecords += parseCsvStream(zis, batchConsumer);
                }
                zis.closeEntry();
            }
        }

        return totalRecords;
    }

    public long parseCsvStream(InputStream csvStream, Consumer<List<ServicePointEntity>> batchConsumer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8));
        List<ServicePointEntity> batch = new ArrayList<>(BATCH_SIZE);
        long totalRecords = 0;
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) continue;

            try {
                ServicePointEntity entity = parseLine(line);
                if (entity != null) {
                    batch.add(entity);
                    totalRecords++;

                    if (batch.size() >= BATCH_SIZE) {
                        batchConsumer.accept(batch);
                        batch = new ArrayList<>(BATCH_SIZE);
                    }
                }
            } catch (Exception e) {
                Log.warnf("Skipping malformed line: %s", e.getMessage());
            }
        }

        if (!batch.isEmpty()) {
            batchConsumer.accept(batch);
        }

        return totalRecords;
    }

    ServicePointEntity parseLine(String line) {
        String[] columns = parseCsvLine(line);
        if (columns.length < EXPECTED_COLUMNS) {
            Log.debugf("Line has %d columns, expected %d", columns.length, EXPECTED_COLUMNS);
            return null;
        }

        ServicePointEntity entity = new ServicePointEntity();
        entity.esiid = clean(columns[0]);
        entity.street = clean(columns[1]);
        entity.streetLine2 = clean(columns[2]);
        entity.city = clean(columns[3]);
        entity.state = clean(columns[4]);
        entity.zip = clean(columns[5]);
        entity.county = clean(columns[6]);
        // columns[7] = DUNS (resolved to tdsp_id by IngestionService from the archive metadata)
        entity.meterReadCycle = clean(columns[8]);
        entity.status = clean(columns[9]);
        entity.premiseType = clean(columns[10]);
        // columns[11] = power_region (no longer stored; implied by TDSP→ISO relationship)
        entity.stationCode = clean(columns[12]);
        entity.stationName = clean(columns[13]);
        entity.metered = "Y".equalsIgnoreCase(clean(columns[14]));
        entity.openServiceOrders = clean(columns[15]);
        entity.polrCustomerClass = clean(columns[16]);
        entity.settlementAmsIndicator = clean(columns[17]);
        entity.tdspAmsIndicator = clean(columns[18]);
        entity.switchHoldIndicator = clean(columns[19]);
        entity.meteredServiceType = clean(columns[20]);
        entity.meteredServiceTypeDesc = clean(columns[21]);

        if (entity.esiid == null || entity.esiid.isBlank()) {
            return null;
        }
        if (entity.status == null || entity.status.isBlank()) {
            entity.status = "Unknown";
        }

        return entity;
    }

    String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    current.append(c);
                }
            } else {
                if (c == '"') {
                    inQuotes = true;
                } else if (c == ',') {
                    fields.add(current.toString());
                    current = new StringBuilder();
                } else {
                    current.append(c);
                }
            }
        }
        fields.add(current.toString());

        return fields.toArray(new String[0]);
    }

    private String clean(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
