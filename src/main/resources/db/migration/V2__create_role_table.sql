CREATE TABLE roles
(
    id          BINARY(16)     NOT NULL,
    name   VARCHAR(200)   NOT NULL,
    description       VARCHAR(100)   NOT NULL,
    created_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP      NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE (name)
);
