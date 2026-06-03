package com.ossrep.servicepoint.service;

import java.time.Instant;

public record ServicePoint(
        Long servicePointId,
        String esiid,
        String street,
        String streetLine2,
        String city,
        String state,
        String zip,
        String county,
        String tdspDuns,
        String townCode,
        String status,
        String premiseType,
        String powerRegion,
        String stationCode,
        String stationName,
        Boolean metered,
        String pendingTransaction,
        Boolean polr,
        String meterType,
        Instant createdAt,
        Instant updatedAt
) {
}
