package com.ossrep.servicepoint.service;

import java.time.Instant;

public record ServicePoint(
        Long servicePointId,
        Long tdspId,
        String esiid,
        String street,
        String streetLine2,
        String city,
        String state,
        String zip,
        String county,
        String meterReadCycle,
        String status,
        String premiseType,
        String stationCode,
        String stationName,
        Boolean metered,
        String openServiceOrders,
        String polrCustomerClass,
        String settlementAmsIndicator,
        String tdspAmsIndicator,
        String switchHoldIndicator,
        String meteredServiceType,
        String meteredServiceTypeDesc,
        Instant createdAt,
        Instant updatedAt
) {
}
