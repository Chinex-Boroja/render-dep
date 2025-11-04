CREATE TABLE roles
(
    id          UUID           NOT NULL DEFAULT gen_random_uuid(),
    name        VARCHAR(200)   NOT NULL,
    description VARCHAR(100)   NOT NULL,
    created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE (name)
);