package org.mpei.repository.jpa;

import org.mpei.model.Booking;
import org.mpei.model.Hotel;
import org.mpei.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingJpaRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByRoomAndStartDateAfterAndEndDateBeforeOrderByStartDate(Room room, LocalDate start, LocalDate end);

    List<Booking> findBookingsByRoom_HotelAndStartDateAfterOrderByStartDate(Hotel hotel, LocalDate start);

    List<Booking> findBookingsByRoom_HotelAndEndDateBeforeOrderByEndDate(Hotel hotel, LocalDate end);

    List<Booking> findBookingsByRoom_HotelAndStartDateAfterAndEndDateBeforeOrderByStartDate(Hotel hotel, LocalDate start, LocalDate end);

    List<Booking> findBookingsByRoom_IdOrderByStartDate(Long roomId);

    List<Booking> findBookingsByUser_UsernameOrderByStartDate(String username);

    List<Booking> findBookingsByRoom_Hotel(Hotel hotel);
}
