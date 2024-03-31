package org.mpei.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpei.dto.BookingDto;
import org.mpei.dto.RoomDto;
import org.mpei.exception.HotelAlreadyBookedException;
import org.mpei.exception.NotAccessException;
import org.mpei.model.*;
import org.mpei.repository.BookingRepository;
import org.mpei.repository.HotelRepository;
import org.mpei.repository.RoomRepository;
import org.mpei.service.BookingService;
import org.mpei.util.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public void saveBooking(Long roomId, LocalDate start, LocalDate end, User user) {
        log.info("Received request to save a booking");

        Room room = roomRepository.getRoomById(roomId);
        List<Booking> lastBookings = bookingRepository.getBookingsBetweenDates(room, start, end);

        if (lastBookings.size() != 0) {
            throw new HotelAlreadyBookedException("Room in this hotel already booked on this dates");
        }
        Booking booking = new Booking(room, start, end, user);

        bookingRepository.saveBooking(booking);
    }

    @Override
    public BookingDto getBookingById(Long id) {
        log.info("Received request to get a booking with id = {}", id);

        Booking booking = bookingRepository.getBookingById(id);

        return BookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public List<BookingDto> getBookingsBetweenDates(String hotelName, LocalDate start, LocalDate end) {
        log.info("Received request to get a bookings by hotelName = {}", hotelName);

        List<Booking> bookings;
        Hotel hotel = hotelRepository.getHotelByName(hotelName);
        if (start == null && end == null) {
            bookings = bookingRepository.getBookingsByHotel(hotel);
        } else if (end == null) {
            bookings = bookingRepository.getBookingsAfterDate(hotel, start);
        } else if (start == null) {
            bookings = bookingRepository.getBookingsBeforeEnd(hotel, end);
        } else {
            bookings = bookingRepository.getBookingsBetweenDates(hotel, start, end);
        }
        return bookings.stream()
            .map(BookingMapper::bookingToBookingDto)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBooking(Long id, String userName) {
        log.info("Received request to delete a booking with bookingId = {} from user with userName = {}", id, userName);

        Booking booking = bookingRepository.getBookingById(id);
        if (!booking.getUser().getUsername().equals(userName)) {
            throw new NotAccessException("User with userName = " + userName + " can't delete this booking with id = " + id);
        }
        bookingRepository.deleteBooking(id);
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        log.info("Received request to delete a booking with bookingId = {}", id);

        bookingRepository.deleteBooking(id);
    }

    @Override
    public List<BookingPeriod> getBookingsByRoomId(Long roomId) {
        log.info("Received request to get a booking periods by roomId = {}", roomId);

        Room room = roomRepository.getRoomById(roomId);
        RoomDto roomDto = new RoomDto(room.getId(), room.getType(), room.getPrice(), room.getCapacity());
        List<RoomDto> roomDtos = List.of(roomDto);
        List<Booking> bookings = bookingRepository.getBookingsByRoomId(roomId);

        return bookings.stream()
            .map(booking -> new BookingPeriod(roomDtos, booking.getStartDate(), booking.getEndDate()))
            .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByUserId(String username) {
        log.info("Received request to get a bookings by username = {}", username);

        List<Booking> bookings = bookingRepository.getBookingsByUserId(username);

        return bookings.stream()
            .map(BookingMapper::bookingToBookingDto)
            .collect(Collectors.toList());
    }
}

