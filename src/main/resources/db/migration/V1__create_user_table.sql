CREATE TABLE users
(
    id          BINARY(16)     NOT NULL,
    full_name   VARCHAR(200)   NOT NULL,
    email       VARCHAR(100)   NOT NULL,
    password    VARCHAR(255)   NOT NULL,
    created_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP      NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_email UNIQUE (email)
);
