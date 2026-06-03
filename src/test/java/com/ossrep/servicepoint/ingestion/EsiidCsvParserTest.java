package com.ossrep.servicepoint.ingestion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ossrep.servicepoint.repository.ServicePointEntity;

import static org.junit.jupiter.api.Assertions.*;

class EsiidCsvParserTest {

    private EsiidCsvParser parser;

    @BeforeEach
    void setUp() {
        parser = new EsiidCsvParser();
    }

    @Test
    void parseLine_allColumns_mapsCorrectly() {
        String line = "\"1008901023817895470106\",\"206 BOXWOOD DR\",\"APT 2\",\"BAYTOWN\",\"TX\",\"77520\","
                + "\"CHAMBERS\",\"957877905\",\"19\",\"Active\",\"Small Non-Residential\",\"ERCOT\","
                + "\"_CD\",\"CEDAR BAYOU OLD\",\"Y\",\"06/01/2026,Move In\",\"Small Non-Residential\","
                + "\"Y\",\"AMSM\",\"N\",\"M04\",\"FIREWORKS STAND\"";

        ServicePointEntity entity = parser.parseLine(line);

        assertNotNull(entity);
        assertEquals("1008901023817895470106", entity.esiid);
        assertEquals("206 BOXWOOD DR", entity.street);
        assertEquals("APT 2", entity.streetLine2);
        assertEquals("BAYTOWN", entity.city);
        assertEquals("TX", entity.state);
        assertEquals("77520", entity.zip);
        assertEquals("CHAMBERS", entity.county);
        // columns[7] (DUNS) and columns[11] (power_region) are skipped -- resolved by IngestionService
        assertEquals("19", entity.meterReadCycle);
        assertEquals("Active", entity.status);
        assertEquals("Small Non-Residential", entity.premiseType);
        assertEquals("_CD", entity.stationCode);
        assertEquals("CEDAR BAYOU OLD", entity.stationName);
        assertTrue(entity.metered);
        assertEquals("06/01/2026,Move In", entity.openServiceOrders);
        assertEquals("Small Non-Residential", entity.polrCustomerClass);
        assertEquals("Y", entity.settlementAmsIndicator);
        assertEquals("AMSM", entity.tdspAmsIndicator);
        assertEquals("N", entity.switchHoldIndicator);
        assertEquals("M04", entity.meteredServiceType);
        assertEquals("FIREWORKS STAND", entity.meteredServiceTypeDesc);
    }

    @Test
    void parseLine_emptyOptionalFields_mapsNulls() {
        String line = "\"1008901023817895470106\",\"206 BOXWOOD DR\",,\"BAYTOWN\",\"TX\",\"77520\","
                + ",\"957877905\",\"19\",\"Inactive\",\"Residential\",\"ERCOT\","
                + "\"_CD\",\"CEDAR BAYOU OLD\",\"N\",,\"Residential\","
                + "\"N\",,\"N\",,";

        ServicePointEntity entity = parser.parseLine(line);

        assertNotNull(entity);
        assertEquals("1008901023817895470106", entity.esiid);
        assertNull(entity.streetLine2);
        assertNull(entity.county);
        assertFalse(entity.metered);
        assertNull(entity.openServiceOrders);
        assertNull(entity.tdspAmsIndicator);
        assertNull(entity.meteredServiceType);
        assertNull(entity.meteredServiceTypeDesc);
    }

    @Test
    void parseLine_meteredYN_convertsToBoolean() {
        String meteredLine = buildMinimalLine("Y");
        String unmeteredLine = buildMinimalLine("N");

        assertTrue(parser.parseLine(meteredLine).metered);
        assertFalse(parser.parseLine(unmeteredLine).metered);
    }

    @Test
    void parseLine_blankEsiid_returnsNull() {
        String line = "\"\",\"206 BOXWOOD DR\",,\"BAYTOWN\",\"TX\",\"77520\","
                + ",\"957877905\",\"19\",\"Active\",\"Residential\",\"ERCOT\","
                + "\"_CD\",\"CEDAR BAYOU OLD\",\"Y\",,\"Residential\","
                + "\"N\",,\"N\",,";

        assertNull(parser.parseLine(line));
    }

    @Test
    void parseLine_tooFewColumns_returnsNull() {
        String line = "\"1008901023817895470106\",\"206 BOXWOOD DR\"";

        assertNull(parser.parseLine(line));
    }

    @Test
    void parseCsvLine_quotedCommas_handledCorrectly() {
        String line = "\"value,with,commas\",\"normal\",\"\"";
        String[] columns = parser.parseCsvLine(line);

        assertEquals("value,with,commas", columns[0]);
        assertEquals("normal", columns[1]);
        assertEquals("", columns[2]);
    }

    @Test
    void parseCsvLine_escapedQuotes_handledCorrectly() {
        String line = "\"value \"\"quoted\"\" here\",\"normal\"";
        String[] columns = parser.parseCsvLine(line);

        assertEquals("value \"quoted\" here", columns[0]);
        assertEquals("normal", columns[1]);
    }

    @Test
    void parseCsvStream_multipleBatches_callsConsumer() throws IOException {
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            csv.append(buildMinimalLine("Y")).append("\n");
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(csv.toString().getBytes(StandardCharsets.UTF_8));
        List<List<ServicePointEntity>> batches = new ArrayList<>();

        long count = parser.parseCsvStream(stream, batches::add);

        assertEquals(12, count);
        assertFalse(batches.isEmpty());
    }

    @Test
    void parseZipStream_extractsCsvFromZip() throws IOException {
        String csvContent = buildMinimalLine("Y") + "\n" + buildMinimalLine("N") + "\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            zos.putNextEntry(new ZipEntry("CENTERPOINT_FUL_20260601.csv"));
            zos.write(csvContent.getBytes(StandardCharsets.UTF_8));
            zos.closeEntry();
        }

        ByteArrayInputStream zipStream = new ByteArrayInputStream(baos.toByteArray());
        List<List<ServicePointEntity>> batches = new ArrayList<>();

        long count = parser.parseZipStream(zipStream, batches::add);

        assertEquals(2, count);
        assertEquals(1, batches.size());
        assertEquals(2, batches.get(0).size());
    }

    private String buildMinimalLine(String metered) {
        return "\"1008901023817895470106\",\"206 BOXWOOD DR\",,\"BAYTOWN\",\"TX\",\"77520\","
                + "\"CHAMBERS\",\"957877905\",\"19\",\"Active\",\"Residential\",\"ERCOT\","
                + "\"_CD\",\"CEDAR BAYOU OLD\",\"" + metered + "\",,\"Residential\","
                + "\"N\",\"AMSM\",\"N\",,";
    }
}
