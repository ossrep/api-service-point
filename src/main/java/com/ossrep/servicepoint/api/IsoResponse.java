package com.ossrep.servicepoint.api;

import java.time.Instant;

import com.ossrep.servicepoint.service.Iso;

public record IsoResponse(
        Long isoId,
        String code,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt
) {

    public static IsoResponse from(Iso iso) {
        return new IsoResponse(
                iso.isoId(),
                iso.code(),
                iso.name(),
                iso.description(),
                iso.createdAt(),
                iso.updatedAt()
        );
    }
}
