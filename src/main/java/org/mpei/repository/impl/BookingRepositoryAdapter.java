package org.mpei.repository.impl;

import lombok.RequiredArgsConstructor;
import org.mpei.exception.error_404.UserNotFoundException;
import org.mpei.model.Booking;
import org.mpei.model.Hotel;
import org.mpei.model.Room;
import org.mpei.repository.BookingRepository;
import org.mpei.repository.jpa.BookingJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryAdapter implements BookingRepository {
    private final BookingJpaRepository bookingJpaRepository;

    @Override
    public void saveBooking(Booking booking) {
        bookingJpaRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingJpaRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Booking with id = " + id + " not found"));
    }

    @Override
    public List<Booking> getBookingsBetweenDates(Room room, LocalDate start, LocalDate end) {
        return bookingJpaRepository.findBookingsByRoomAndStartDateAfterAndEndDateBeforeOrderByStartDate(room, start, end);
    }

    @Override
    public List<Booking> getBookingsBetweenDates(Hotel hotel, LocalDate start, LocalDate end) {
        return bookingJpaRepository.findBookingsByRoom_HotelAndStartDateAfterAndEndDateBeforeOrderByStartDate(hotel, start, end);
    }

    @Override
    public List<Booking> getBookingsAfterDate(Hotel hotel, LocalDate start) {
        return bookingJpaRepository.findBookingsByRoom_HotelAndStartDateAfterOrderByStartDate(hotel, start);
    }

    @Override
    public List<Booking> getBookingsBeforeEnd(Hotel hotel, LocalDate end) {
        return bookingJpaRepository.findBookingsByRoom_HotelAndEndDateBeforeOrderByEndDate(hotel, end);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingJpaRepository.deleteById(id);
    }

    @Override
    public List<Booking> getBookingsByHotel(Hotel hotel) {
        return bookingJpaRepository.findBookingsByRoom_Hotel(hotel);
    }

    @Override
    public List<Booking> getBookingsByRoomId(Long roomId) {
        return bookingJpaRepository.findBookingsByRoom_IdOrderByStartDate(roomId);
    }

    @Override
    public List<Booking> getBookingsByUserId(String username) {
        return bookingJpaRepository.findBookingsByUser_UsernameOrderByStartDate(username);
    }
}
