package com.ossrep.servicepoint.ingestion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateIngestionLogRequest(
        @NotNull Long tdspId,
        @NotBlank String fileName,
        @NotBlank String fileType
) {
}
