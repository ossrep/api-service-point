CREATE TABLE iso (
    iso_id      BIGSERIAL    PRIMARY KEY,
    code        TEXT         NOT NULL UNIQUE,
    name        TEXT         NOT NULL,
    description TEXT,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO iso (iso_id, code, name, description)
VALUES (1, 'ERCOT', 'Electric Reliability Council of Texas', 'Independent system operator managing the flow of electric power to more than 26 million Texas customers');
