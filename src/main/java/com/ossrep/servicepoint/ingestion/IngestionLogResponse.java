package com.ossrep.servicepoint.ingestion;

import java.time.Instant;

public record IngestionLogResponse(
        Long ingestionLogId,
        Long tdspId,
        String fileName,
        String fileType,
        Long recordCount,
        String status,
        Instant startedAt,
        Instant completedAt,
        String errorMessage
) {

    public static IngestionLogResponse from(IngestionLogEntity entity) {
        return new IngestionLogResponse(
                entity.ingestionLogId,
                entity.tdspId,
                entity.fileName,
                entity.fileType,
                entity.recordCount,
                entity.status,
                entity.startedAt,
                entity.completedAt,
                entity.errorMessage
        );
    }
}
