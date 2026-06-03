package com.ossrep.servicepoint.ingestion;

import jakarta.validation.constraints.NotBlank;

public record UpdateIngestionLogRequest(
        @NotBlank String status,
        Long recordCount,
        String errorMessage
) {
}
