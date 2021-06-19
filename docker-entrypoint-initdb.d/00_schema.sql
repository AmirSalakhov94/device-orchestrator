CREATE TABLE route
(
    id          UUID NOT NULL PRIMARY KEY,
    session_id  UUID,
    device_id   UUID,
    is_active   BOOLEAN,
    start       TIMESTAMP,
    end         TIMESTAMP,
    create_date TIMESTAMP
);

CREATE TABLE state
(
    id                  UUID NOT NULL PRIMARY KEY,
    device_id           UUID,
    route_id            UUID,
    lat                 DOUBLE PRECISION,
    lng                 DOUBLE PRECISION,
    speed_in_meters     REAL,
    charging_percentage REAL,
    record_time         TIMESTAMP,
    create_date         TIMESTAMP
);