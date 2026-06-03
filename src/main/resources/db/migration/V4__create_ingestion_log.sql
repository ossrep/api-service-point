CREATE TABLE ingestion_log (
    ingestion_log_id BIGSERIAL PRIMARY KEY,
    tdsp_id          BIGINT    NOT NULL REFERENCES tdsp(tdsp_id),
    file_name        TEXT      NOT NULL UNIQUE,
    file_type        TEXT      NOT NULL,
    record_count     BIGINT,
    status           TEXT      NOT NULL,
    started_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at     TIMESTAMP,
    error_message    TEXT
);
