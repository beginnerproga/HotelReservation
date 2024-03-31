package org.mpei.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpei.dto.HotelDto;
import org.mpei.dto.RoomDto;
import org.mpei.model.Amenity;
import org.mpei.model.Room;
import org.mpei.repository.HotelRepository;
import org.mpei.service.HotelService;
import org.mpei.util.mapper.HotelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public void saveHotel(HotelDto hotelDto) {
        log.info("Received request to save a hotel");

        var hotel = HotelMapper.hotelDtoToHotel(hotelDto);
        hotelRepository.saveHotel(hotel);
    }

    @Override
    @Transactional
    public void updateHotel(HotelDto hotelDto, Boolean roomUpdated) {
        log.info("Received request to save a hotel");

        var existingHotel = hotelRepository.getHotelByName(hotelDto.getName());

        if (hotelDto.getName() != null) {
            existingHotel.setName(hotelDto.getName());
        }
        if (hotelDto.getDescription() != null) {
            existingHotel.setDescription(hotelDto.getDescription());
        }
        if (hotelDto.getAddress() != null) {
            existingHotel.setAddress(hotelDto.getAddress());
        }
        if (hotelDto.getRating() != null) {
            existingHotel.setRating(hotelDto.getRating());
        }
        if (hotelDto.getAmenities() != null) {
            existingHotel.setAmenities(hotelDto.getAmenities()
                .stream()
                .map(Amenity::valueOf)
                .collect(Collectors.toList()));
        }
        if (hotelDto.getImage() != null) {
            existingHotel.setImage(hotelDto.getImage());
        }
        if (hotelDto.getLocation() != null) {
            existingHotel.setLocation(hotelDto.getLocation());
            if (roomUpdated) {
                var existingRooms = existingHotel.getRooms();
                var roomDtos = hotelDto.getRooms();

                existingRooms.clear();
                for (RoomDto roomDto : roomDtos) {
                    var room = new Room(roomDto.getType(), roomDto.getPrice(), roomDto.getCapacity(), existingHotel);
                    existingRooms.add(room);
                }
            }
            hotelRepository.saveHotel(existingHotel);
        }
    }

    @Override
    public List<HotelDto> filterHotels(String amenities, Integer rating, String location, String sort) {
        log.info("Received request to filter hotels");

        var amenitiesEnum = new ArrayList<Amenity>();
        if (amenities != null && !amenities.isBlank()) {
            amenitiesEnum = (ArrayList<Amenity>) stringToAmenityList(amenities);
        }
        List<HotelDto> filteredHotels = hotelRepository.filterHotels(amenitiesEnum, rating, location)
            .stream()
            .map(HotelMapper::hotelToHotelDto)
            .collect(Collectors.toList());

        if ("ratingAsc".equals(sort)) {
            filteredHotels.sort(Comparator.comparing(HotelDto::getRating));
        } else if ("ratingDesc".equals(sort)) {
            filteredHotels.sort(Comparator.comparing(HotelDto::getRating).reversed());
        }

        return filteredHotels;
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Received request to get a hotel with id = {}", id);

        var hotel = hotelRepository.getHotelById(id);

        return HotelMapper.hotelToHotelDto(hotel);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("Received request to get all hotels");

        var hotels = hotelRepository.getAllHotels();

        return hotels.stream()
            .map(HotelMapper::hotelToHotelDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteHotel(Long id) {
        log.info("Received request to delete a hotel with id = {}", id);

        hotelRepository.deleteHotel(id);
    }

    @Override
    public HotelDto getHotelByName(String name) {
        log.info("Received request to get a hotel with name = {}", name);

        var hotel = hotelRepository.getHotelByName(name);

        return HotelMapper.hotelToHotelDto(hotel);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================
    private List<Amenity> stringToAmenityList(String amenities) {
        return Arrays.stream(amenities.split(","))
            .map(String::trim)
            .map(String::toUpperCase)
            .flatMap(amenity -> {
                try {
                    return Stream.of(Amenity.valueOf(amenity));
                } catch (Exception e) {
                    return Stream.empty();
                }
            })
            .collect(Collectors.toList());
    }
}
