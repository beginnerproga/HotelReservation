package org.mpei.util.mapper;

import org.mpei.dto.HotelDto;
import org.mpei.dto.RoomDto;
import org.mpei.model.Amenity;
import org.mpei.model.Hotel;
import org.mpei.model.Room;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HotelMapper {
    public static HotelDto hotelToHotelDto(Hotel hotel) {
        var roomsDto = new ArrayList<RoomDto>();
        for (var room : hotel.getRooms()) {
            roomsDto.add(new RoomDto(room.getId(), room.getType(), room.getPrice(), room.getCapacity()));
        }

        return new HotelDto(hotel.getId(), hotel.getName(), hotel.getDescription(), hotel.getAddress(), hotel.getRating(), hotel.getAmenities()
            .stream()
            .map(Amenity::getDisplayName)
            .collect(Collectors.toList()), roomsDto,
            hotel.getImage(), hotel.getLocation());
    }

    public static Hotel hotelDtoToHotel(HotelDto hotelDto) {
        var hotel =
            new Hotel(hotelDto.getName(), hotelDto.getDescription(), hotelDto.getAddress(), hotelDto.getRating(),
                hotelDto.getAmenities()
                    .stream()
                    .map(Amenity::valueOf)
                    .collect(Collectors.toList()), new ArrayList<>(),
                hotelDto.getImage(), hotelDto.getLocation());
        var rooms = new ArrayList<Room>();
        for (RoomDto roomDto : hotelDto.getRooms()) {
            rooms.add(new Room(roomDto.getType(), roomDto.getPrice(), roomDto.getCapacity(), hotel));
        }
        hotel.setRooms(rooms);

        return hotel;
    }
}
