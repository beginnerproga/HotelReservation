package org.mpei.repository;

import org.mpei.model.Amenity;
import org.mpei.model.Hotel;

import java.util.List;

public interface HotelRepository {
    void saveHotel(Hotel hotel);

    Hotel getHotelById(Long id);

    List<Hotel> getAllHotels();

    void deleteHotel(Long id);

    Hotel getHotelByName(String name);

    List<Hotel> filterHotels(List<Amenity> amenities, Integer rating, String location);
}
