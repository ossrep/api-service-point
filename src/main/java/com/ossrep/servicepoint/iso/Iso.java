package com.ossrep.servicepoint.iso;

import java.time.Instant;

public record Iso(
        Long isoId,
        String code,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
