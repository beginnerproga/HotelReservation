package org.mpei.repository.impl;

import lombok.RequiredArgsConstructor;
import org.mpei.exception.SameEmailException;
import org.mpei.exception.error_404.HotelNotFoundException;
import org.mpei.model.Amenity;
import org.mpei.model.Hotel;
import org.mpei.repository.HotelRepository;
import org.mpei.repository.jpa.HotelJpaRepository;
import org.mpei.repository.jpa.specification.HotelSpecifications;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {
    private final HotelJpaRepository hotelJpaRepository;

    @Override
    public void saveHotel(Hotel hotel) {
        try {
            hotelJpaRepository.save(hotel);
        } catch (Exception e) {
            throw new SameEmailException("Hotel with this description or name already created");
        }
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelJpaRepository.findById(id)
            .orElseThrow(() -> new HotelNotFoundException("Hotel with id = " + id + " not found"));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelJpaRepository.findAll();
    }

    @Override
    public void deleteHotel(Long id) {
        hotelJpaRepository.deleteById(id);
    }

    @Override
    public Hotel getHotelByName(String name) {
        return hotelJpaRepository.findHotelByName(name)
            .orElseThrow(() -> new HotelNotFoundException("Hotel with name = " + name + " not found"));
    }

    @Override
    public List<Hotel> filterHotels(List<Amenity> amenities, Integer rating, String location) {
        return hotelJpaRepository.findAll(HotelSpecifications.withDynamicQuery(location, amenities, rating));
    }
}
