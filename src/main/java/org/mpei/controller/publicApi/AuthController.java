package org.mpei.controller.publicApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpei.dto.JwtAuthenticationResponse;
import org.mpei.dto.SignInRequest;
import org.mpei.dto.SignUpRequest;
import org.mpei.service.UserService;
import org.mpei.service.impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Контроллер для регистрации и авторизации пользователей.")
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request, HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = authenticationService.signUp(request);
        addTokenToCookie(jwtResponse.getToken(), response);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request, HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = authenticationService.signIn(request);
        addTokenToCookie(jwtResponse.getToken(), response);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Выход из аккаунта")
    @PostMapping("/sign-out")
    public ResponseEntity<?> signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwtToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Проверка статуса авторизации пользователя")
    @GetMapping("/status")
    public ResponseEntity<?> authStatus(HttpServletRequest request) {
        try {
            userService.getCurrentUser();
        } catch (RuntimeException e) {
            return ResponseEntity.ok().body(Map.of("isAuthenticated", false));
        }
        return ResponseEntity.ok().body(Map.of("isAuthenticated", true));
    }
    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void addTokenToCookie(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}