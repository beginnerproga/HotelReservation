package org.mpei.controller.privateApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpei.service.BookingService;
import org.mpei.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/booking")
@Tag(name = "Контроллер для управления бронированиями - приватный", description = "Позволяет добавить новое бронирование, удалить своё бронирование")
@Slf4j
public class BookingPrivateController {
    private final BookingService bookingService;
    private final UserService userService;

    @Operation(summary = "Добавление нового бронирования", description = "Позволяет пользователю добавить новое бронирование, указав ID номера, дату начала и дату окончания пребывания")
    @PostMapping
    public ResponseEntity<Void> addBooking(@RequestParam("roomSelection") Long roomId,
        @RequestParam("startDate") LocalDate startDate,
        @RequestParam("endDate") LocalDate endDate) {
        bookingService.saveBooking(roomId, startDate, endDate, userService.getCurrentUser());

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление бронирования по ID", description = "Позволяет пользователю удалить существующее бронирование по его уникальному идентификатору")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId, userService.getCurrentUser().getUsername());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}