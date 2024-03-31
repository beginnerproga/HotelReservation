package org.mpei.controller.adminApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mpei.dto.HotelDto;
import org.mpei.service.HotelService;
import org.mpei.util.Create;
import org.mpei.util.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotel")
@Tag(name = "Контроллер для управления отелями - закрытый", description = "Позволяет добавить новый отель, частично или полностью обновить его, удалить, получить отель/отели по id, имени, получить список всех отелей")
@Secured("ADMIN")
public class HotelAdminController {
    private final HotelService hotelService;

    @Operation(summary = "Добавление нового отеля", description = "Создает новую запись отеля с предоставленными деталями")
    @PostMapping
    public ResponseEntity<Void> saveHotel(@RequestBody @Validated({Create.class}) HotelDto hotelDto) {
        hotelService.saveHotel(hotelDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Получение отеля по имени", description = "Возвращает детали отеля по его имени")
    @GetMapping
    public ResponseEntity<HotelDto> getHotelByName(@RequestParam(name = "name") String name) {
        HotelDto hotel = hotelService.getHotelByName(name);

        return ResponseEntity.ok(hotel);
    }

    @Operation(summary = "Частичное или полное обновление отеля", description = "Обновляет детали существующего отеля. Также можно указать, были ли обновлены комнаты отеля")
    @PatchMapping
    public ResponseEntity<Void> updateHotel(@RequestBody @Validated({Update.class}) HotelDto hotelDto,
        @RequestParam(name = "roomUpdated") Boolean roomUpdated) {
        hotelService.updateHotel(hotelDto, roomUpdated);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Получение отеля по ID", description = "Возвращает детали отеля по его уникальному идентификатору")
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        HotelDto hotel = hotelService.getHotelById(hotelId);

        return ResponseEntity.ok(hotel);
    }

    @Operation(summary = "Получение списка всех отелей", description = "Возвращает список всех отелей в системе")
    @GetMapping("/all")
    public ResponseEntity<List<HotelDto>> getHotels() {
        List<HotelDto> hotels = hotelService.getAllHotels();

        return ResponseEntity.ok(hotels);
    }

    @Operation(summary = "Удаление отеля по ID", description = "Удаляет отель по его уникальному идентификатору")
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}