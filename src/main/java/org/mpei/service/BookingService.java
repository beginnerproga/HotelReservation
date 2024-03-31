package org.mpei.service;

import org.mpei.dto.BookingDto;
import org.mpei.model.BookingPeriod;
import org.mpei.model.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void saveBooking(Long roomId, LocalDate start, LocalDate end, User user);

    BookingDto getBookingById(Long id);

    List<BookingDto> getBookingsBetweenDates(String hotelName, LocalDate start, LocalDate end);

    void deleteBooking(Long id, String username);

    void deleteBooking(Long id);

    List<BookingPeriod> getBookingsByRoomId(Long roomId);

    List<BookingDto> getBookingsByUserId(String username);
}
