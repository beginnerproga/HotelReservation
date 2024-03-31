package org.mpei.service;

import org.mpei.dto.HotelDto;

import java.util.List;

public interface HotelService {
    void saveHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    List<HotelDto> getAllHotels();

    void deleteHotel(Long id);

    HotelDto getHotelByName(String name);

    void updateHotel(HotelDto hotelDto, Boolean roomUpdated);

    List<HotelDto> filterHotels(String amenities, Integer rating, String location, String sort);
}
