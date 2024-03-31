package org.mpei.repository;

import org.mpei.model.Booking;
import org.mpei.model.Hotel;
import org.mpei.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    void saveBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getBookingsBetweenDates(Room room, LocalDate start, LocalDate end);

    List<Booking> getBookingsAfterDate(Hotel hotel, LocalDate start);

    List<Booking> getBookingsBeforeEnd(Hotel hotel, LocalDate end);

    void deleteBooking(Long id);

    List<Booking> getBookingsByHotel(Hotel hotel);

    List<Booking> getBookingsByRoomId(Long roomId);

    List<Booking> getBookingsByUserId(String username);

    List<Booking> getBookingsBetweenDates(Hotel hotel, LocalDate start, LocalDate end);
}
