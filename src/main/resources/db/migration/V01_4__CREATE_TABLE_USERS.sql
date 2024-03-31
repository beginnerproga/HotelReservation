CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    role     VARCHAR(255) NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id)
);
