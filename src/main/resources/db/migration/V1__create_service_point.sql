CREATE TABLE service_point (
    service_point_id    BIGSERIAL    PRIMARY KEY,
    esiid               TEXT         NOT NULL UNIQUE,
    street              TEXT,
    street_line2        TEXT,
    city                TEXT,
    state               TEXT,
    zip                 TEXT,
    county              TEXT,
    tdsp_duns           TEXT,
    town_code           TEXT,
    status              TEXT         NOT NULL,
    premise_type        TEXT,
    power_region        TEXT,
    station_code        TEXT,
    station_name        TEXT,
    metered             BOOLEAN      NOT NULL DEFAULT FALSE,
    pending_transaction TEXT,
    polr                BOOLEAN      NOT NULL DEFAULT FALSE,
    meter_type          TEXT,
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER SEQUENCE service_point_service_point_id_seq RESTART 1000000000000000000;
