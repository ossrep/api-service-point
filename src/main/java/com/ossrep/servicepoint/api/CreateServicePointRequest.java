package com.ossrep.servicepoint.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateServicePointRequest(
        @NotBlank(message = "{servicePoint.esiid.notBlank}")
        String esiid,
        String street,
        String streetLine2,
        String city,
        String state,
        String zip,
        String county,
        String tdspDuns,
        String meterReadCycle,
        @NotNull(message = "{servicePoint.status.notNull}")
        String status,
        String premiseType,
        String powerRegion,
        String stationCode,
        String stationName,
        @NotNull(message = "{servicePoint.metered.notNull}")
        Boolean metered,
        String openServiceOrders,
        String polrCustomerClass,
        String settlementAmsIndicator,
        String tdspAmsIndicator,
        String switchHoldIndicator,
        String meteredServiceType,
        String meteredServiceTypeDesc
) {
}
