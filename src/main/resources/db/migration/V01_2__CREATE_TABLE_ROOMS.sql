CREATE TABLE IF NOT EXISTS rooms
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY,
    type     VARCHAR(100) NOT NULL,
    price    REAL         NOT NULL,
    capacity INTEGER      NOT NULL,
    hotel_id BIGINT       NOT NULL,
    CONSTRAINT PK_ROOMS PRIMARY KEY (id),
    CONSTRAINT FK_ROOMS_HOTELS FOREIGN KEY (hotel_id)
        references hotels (id) ON DELETE CASCADE
);