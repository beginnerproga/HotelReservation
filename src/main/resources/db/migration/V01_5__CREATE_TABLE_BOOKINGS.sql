CREATE TABLE IF NOT EXISTS bookings
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    room_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT PK_BOOKINGS PRIMARY KEY (id),
    CONSTRAINT FK_BOOKINGS_ROOMS FOREIGN KEY (room_id)
    references rooms (id) ON DELETE CASCADE,
    CONSTRAINT FK_BOOKINGS_USERS FOREIGN KEY (user_id)
    references users (id) ON DELETE CASCADE

    )