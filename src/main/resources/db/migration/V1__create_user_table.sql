CREATE TABLE users
(
    id          UUID           NOT NULL DEFAULT gen_random_uuid(),
    full_name   VARCHAR(200)   NOT NULL,
    email       VARCHAR(100)   NOT NULL,
    password    VARCHAR(255)   NOT NULL,
    role_id     UUID           NOT NULL,
    created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_email UNIQUE (email),
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id)
);