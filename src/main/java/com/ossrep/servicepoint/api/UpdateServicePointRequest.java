package com.ossrep.servicepoint.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateServicePointRequest(
        @NotBlank(message = "{servicePoint.esiid.notBlank}")
        String esiid,
        String street,
        String streetLine2,
        String city,
        String state,
        String zip,
        String county,
        String tdspDuns,
        String townCode,
        @NotNull(message = "{servicePoint.status.notNull}")
        String status,
        String premiseType,
        String powerRegion,
        String stationCode,
        String stationName,
        @NotNull(message = "{servicePoint.metered.notNull}")
        Boolean metered,
        String pendingTransaction,
        @NotNull(message = "{servicePoint.polr.notNull}")
        Boolean polr,
        String meterType
) {
}
