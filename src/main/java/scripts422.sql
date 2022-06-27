CREATE TABLE car
(
    id    SERIAL NOT NULL PRIMARY KEY,
    marka VARCHAR,
    model VARCHAR,
    cost  NUMERIC(9, 2)
);

CREATE TABLE chel
(
    id     SERIAL NOT NULL PRIMARY KEY,
    name   VARCHAR,
    age    INTEGER,
    rights BOOLEAN,
    car_id INTEGER REFERENCES car (id)
)