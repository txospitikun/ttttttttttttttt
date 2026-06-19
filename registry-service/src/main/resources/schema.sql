-- Tabela in care registrul tine evidenta procesoarelor de flux active.
-- Este rulata automat de Spring Boot la pornire (baza de date embedded H2).
CREATE TABLE IF NOT EXISTS flow_processors (
    id            VARCHAR(64)  PRIMARY KEY,
    name          VARCHAR(128) NOT NULL,
    host          VARCHAR(128) NOT NULL,
    port          INT          NOT NULL,
    type          VARCHAR(64)  NOT NULL,
    registered_at TIMESTAMP    NOT NULL
);
