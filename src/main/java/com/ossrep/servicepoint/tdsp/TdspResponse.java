package com.ossrep.servicepoint.tdsp;

import java.time.Instant;

public record TdspResponse(
        Long tdspId,
        Long isoId,
        String code,
        String name,
        String duns,
        String description,
        Instant createdAt,
        Instant updatedAt
) {

    public static TdspResponse from(Tdsp tdsp) {
        return new TdspResponse(
                tdsp.tdspId(),
                tdsp.isoId(),
                tdsp.code(),
                tdsp.name(),
                tdsp.duns(),
                tdsp.description(),
                tdsp.createdAt(),
                tdsp.updatedAt()
        );
    }
}
