CREATE TABLE tdsp (
    tdsp_id     BIGSERIAL    PRIMARY KEY,
    iso_id      BIGINT       NOT NULL REFERENCES iso(iso_id),
    code        TEXT         NOT NULL UNIQUE,
    name        TEXT         NOT NULL,
    duns        TEXT         UNIQUE,
    description TEXT,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tdsp (tdsp_id, iso_id, code, name, duns, description) VALUES
    (1,  1, 'CENTERPOINT',  'CenterPoint Energy',                '957877905', 'Houston and surrounding areas'),
    (2,  1, 'ONCOR_ELEC',   'Oncor Electric Delivery',            '007924772', 'Dallas/Fort Worth and surrounding areas'),
    (3,  1, 'AEP_CENTRAL',  'AEP Texas Central',                  '006389120', 'South and West Texas'),
    (4,  1, 'AEP_NORTH',    'AEP Texas North',                    '006272835', 'North and West Texas'),
    (5,  1, 'TNMP',         'Texas-New Mexico Power',             '007923964', 'Parts of Texas near New Mexico border'),
    (6,  1, 'LUBBOCK',      'Lubbock Power and Light',            '826155982', 'Lubbock area'),
    (7,  1, 'NUECES_ELEC',  'Nueces Electric Cooperative',        '171519786', 'Corpus Christi area'),
    (8,  1, 'ENTERGY_GULF', 'Entergy Texas',                      NULL,        'Southeast Texas'),
    (9,  1, 'SHARYLAND',    'Sharyland Utilities',                NULL,        'Parts of South and West Texas'),
    (10, 1, 'SWEPCO_ENERG', 'Southwestern Electric Power Company',NULL,        'Northeast Texas'),
    (11, 1, 'AEP_TEXAS_SP', 'AEP Texas',                          NULL,        'AEP Texas service area');
