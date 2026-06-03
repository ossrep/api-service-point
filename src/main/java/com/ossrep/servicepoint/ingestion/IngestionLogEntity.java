package com.ossrep.servicepoint.ingestion;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "IngestionLog")
@Table(name = "ingestion_log")
public class IngestionLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingestion_log_id", nullable = false)
    public Long ingestionLogId;

    @Column(name = "file_name", nullable = false, unique = true)
    public String fileName;

    @Column(name = "file_type", nullable = false)
    public String fileType;

    @Column(name = "tdsp", nullable = false)
    public String tdsp;

    @Column(name = "record_count")
    public Long recordCount;

    @Column(name = "status", nullable = false)
    public String status;

    @Column(name = "started_at", nullable = false)
    public Instant startedAt;

    @Column(name = "completed_at")
    public Instant completedAt;

    @Column(name = "error_message")
    public String errorMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngestionLogEntity that = (IngestionLogEntity) o;
        return Objects.equals(ingestionLogId, that.ingestionLogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingestionLogId);
    }
}
