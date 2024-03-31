package org.mpei.controller.frontApi;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mpei.dto.BookingDto;
import org.mpei.dto.HotelDto;
import org.mpei.model.User;
import org.mpei.service.BookingService;
import org.mpei.service.HotelService;
import org.mpei.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Контроллер thymeleaf фронт", description = "Позволяет получить html страницы для взаимодействия с сервисом бронирования отелей")

public class FrontController {
    private final BookingService bookingService;
    private final HotelService hotelService;
    private final UserService userService;

    @GetMapping
    public String listHotels(@RequestParam(required = false) String amenity,
        @RequestParam(required = false) Integer rating,
        @RequestParam(required = false) String location,
        @RequestParam(required = false) String sort,

        Model model) {
        List<HotelDto> filteredHotels = hotelService.filterHotels(amenity, rating, location, sort);

        model.addAttribute("hotels", filteredHotels);
        model.addAttribute("currentAmenity", amenity);
        model.addAttribute("currentRating", rating);
        model.addAttribute("currentLocation", location);
        model.addAttribute("currentSort", sort);

        return "listHotel";
    }

    @GetMapping("/hotel")
    public String showHotelDetails(@RequestParam("name") String hotelName, Model model) {
        HotelDto hotel = hotelService.getHotelByName(hotelName);
        model.addAttribute("hotel", hotel);

        return "bookingHotel";
    }

    @GetMapping("/user/bookings")
    public String getUserBookings(Model model) {
        List<BookingDto> bookings = bookingService.getBookingsByUserId(userService.getCurrentUser().getUsername());
        model.addAttribute("bookings", bookings);

        return "bookings";
    }

    @GetMapping("/user/info")
    public String getUserInfo(Model model) {
        User user = null;
        try {
            user = userService.getCurrentUser();
        } catch (UsernameNotFoundException e) {

        }
        model.addAttribute("user", user);

        return "userInfo";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
