CREATE TABLE IF NOT EXISTS hotel_amenities
(
    hotel_id BIGINT,
    amenity  VARCHAR(255),
    PRIMARY KEY (hotel_id, amenity),
    FOREIGN KEY (hotel_id) REFERENCES hotels (id)
);