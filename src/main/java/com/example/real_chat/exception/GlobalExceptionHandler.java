package com.example.real_chat.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }




    /**
     * exception 발생사 ResponseEntity 로 변환 후 반환
     * @return ResponseEntity<ErrorResponseDto>
     */
    private ResponseEntity<ErrorResponse> exceptionResponseEntity(String message, String requestUrl) {
        ErrorResponse em = ErrorResponse.builder()
                .message(message)
                .code(HttpStatus.BAD_REQUEST.value())
                .requestUrl(requestUrl)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em);
    }
}
