package com.ossrep.servicepoint.api;

import java.time.Instant;

import com.ossrep.servicepoint.service.ServicePoint;

public record ServicePointResponse(
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

    public static ServicePointResponse from(ServicePoint sp) {
        return new ServicePointResponse(
                sp.servicePointId(),
                sp.esiid(),
                sp.street(),
                sp.streetLine2(),
                sp.city(),
                sp.state(),
                sp.zip(),
                sp.county(),
                sp.tdspDuns(),
                sp.townCode(),
                sp.status(),
                sp.premiseType(),
                sp.powerRegion(),
                sp.stationCode(),
                sp.stationName(),
                sp.metered(),
                sp.pendingTransaction(),
                sp.polr(),
                sp.meterType(),
                sp.createdAt(),
                sp.updatedAt()
        );
    }
}
