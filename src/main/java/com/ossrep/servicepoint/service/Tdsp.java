package com.ossrep.servicepoint.service;

import java.time.Instant;

public record Tdsp(
        Long tdspId,
        Long isoId,
        String code,
        String name,
        String duns,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
