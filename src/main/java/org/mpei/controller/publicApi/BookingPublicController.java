package org.mpei.controller.publicApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpei.model.BookingPeriod;
import org.mpei.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/booking")
@Tag(name = "Контроллер для управления бронированиями - открытый", description = "Позволяет получить все бронирования комнаты в выбранном отеле")
@Slf4j
public class BookingPublicController {
    private final BookingService bookingService;

    @Operation(summary = "Получение всех бронирований комнаты", description = "Позволяет получить список всех периодов бронирования для конкретной комнаты в отеле по уникальному идентификатору комнаты")
    @GetMapping
    public List<BookingPeriod> getBookings(@RequestParam(name = "roomId") Long roomId) {
        return bookingService.getBookingsByRoomId(roomId);
    }
}
