package org.mpei.util.mapper;

import org.mpei.dto.BookingDto;
import org.mpei.model.Booking;

import java.time.temporal.ChronoUnit;

public class BookingMapper {
    public static BookingDto bookingToBookingDto(Booking booking) {
        return new BookingDto(booking.getId(), booking.getRoom().getHotel().getName(), booking.getStartDate(), booking.getEndDate(), calculatePrice(booking));
    }

    public static Double calculatePrice(Booking booking) {
        return ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate()) * booking.getRoom().getPrice();
    }
}
