package com.ossrep.servicepoint.api;

import java.time.Instant;

import com.ossrep.servicepoint.service.ServicePoint;

public record ServicePointResponse(
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

    public static ServicePointResponse from(ServicePoint sp) {
        return new ServicePointResponse(
                sp.servicePointId(),
                sp.tdspId(),
                sp.esiid(),
                sp.street(),
                sp.streetLine2(),
                sp.city(),
                sp.state(),
                sp.zip(),
                sp.county(),
                sp.meterReadCycle(),
                sp.status(),
                sp.premiseType(),
                sp.stationCode(),
                sp.stationName(),
                sp.metered(),
                sp.openServiceOrders(),
                sp.polrCustomerClass(),
                sp.settlementAmsIndicator(),
                sp.tdspAmsIndicator(),
                sp.switchHoldIndicator(),
                sp.meteredServiceType(),
                sp.meteredServiceTypeDesc(),
                sp.createdAt(),
                sp.updatedAt()
        );
    }
}
