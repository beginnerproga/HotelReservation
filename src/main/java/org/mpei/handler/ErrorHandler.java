package org.mpei.handler;

import lombok.extern.slf4j.Slf4j;
import org.mpei.controller.adminApi.BookingAdminController;
import org.mpei.controller.adminApi.HotelAdminController;
import org.mpei.controller.privateApi.BookingPrivateController;
import org.mpei.controller.publicApi.AuthController;
import org.mpei.controller.publicApi.BookingPublicController;
import org.mpei.exception.HotelAlreadyBookedException;
import org.mpei.exception.NotAccessException;
import org.mpei.exception.SameEmailException;
import org.mpei.exception.SameHotelException;
import org.mpei.exception.error_404.BookingNotFoundException;
import org.mpei.exception.error_404.HotelNotFoundException;
import org.mpei.exception.error_404.RoomNotFoundException;
import org.mpei.exception.error_404.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {HotelAdminController.class, BookingAdminController.class, BookingPublicController.class,
    BookingPrivateController.class, AuthController.class})
public class ErrorHandler {

    @ExceptionHandler(value = {SameEmailException.class, SameHotelException.class, HotelAlreadyBookedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(final RuntimeException e) {
        log.error("409 {}", e.getMessage());

        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage()))
            .build();
    }

    @ExceptionHandler(value = {HotelNotFoundException.class, UserNotFoundException.class, BookingNotFoundException.class, RoomNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final RuntimeException e) {
        log.error("404 {}", e.getMessage());

        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error("400 {}", e.getMessage());

        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
            .build();
    }

    @ExceptionHandler({NotAccessException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleAuthorizedExceptions(RuntimeException e) {
        log.error("401 {}", e.getMessage());

        return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage()))
            .build();
    }
}
