package org.mpei.controller.adminApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mpei.dto.BookingDto;
import org.mpei.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/booking")
@Secured("ADMIN")
@Tag(name = "Контроллер для управления бронированиями - закрытый", description = "Позволяет получить бронирование/бронирования по его id, названию отеля и по различным временным промежуткам, а также удалить бронирование")
public class BookingAdminController {
    private final BookingService bookingService;

    @Operation(summary = "Получить бронирование по ID", description = "Возвращает детали бронирования по его уникальному идентификатору")
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) {
        BookingDto bookingDto = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(bookingDto);
    }

    @Operation(summary = "Получить бронирования по названию отеля и датам", description = "Возвращает список бронирований для заданного отеля, опционально ограниченный датами начала и конца")
    @GetMapping
    public ResponseEntity<List<BookingDto>> getBookingByHotel(@RequestParam String hotelName, @RequestParam(required = false) LocalDate start,
        @RequestParam(required = false) LocalDate end) {
        List<BookingDto> bookingDtos = bookingService.getBookingsBetweenDates(hotelName, start, end);

        return ResponseEntity.ok(bookingDtos);
    }

    @Operation(summary = "Удалить бронирование по ID", description = "Удаляет бронирование по его уникальному идентификатору")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}