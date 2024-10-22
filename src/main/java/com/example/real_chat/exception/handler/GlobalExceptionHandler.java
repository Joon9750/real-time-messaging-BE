package com.example.real_chat.exception.handler;

import com.example.real_chat.exception.CannotJoinChatRoomException;
import com.example.real_chat.exception.UnauthorizedException;
import com.example.real_chat.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }

    /**
     * HTTP 요청 본문을 읽을 수 없을 때 발생, 잛못된 클라이언트의 요청
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }

    /**
     * 유저가 채팅방에 들어갈 수 없을 때 발생
     */
    @ExceptionHandler(CannotJoinChatRoomException.class)
    protected ResponseEntity<ErrorResponse> handleCannotJoinChatRoomException(
            CannotJoinChatRoomException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> handleUnauthorizedException(
            UnauthorizedException e,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(e.getMessage(), request.getRequestURI());
    }

    /**
     * 데이터를 찾을 수 없을 때 발생
     */
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e, HttpServletRequest request) {
        return exceptionResponseEntity("존재하지 않는 값입니다.", request.getRequestURI());
    }

    // 500 Uncaught Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception exception,
            HttpServletRequest request
    ) {
        return exceptionResponseEntity(exception.getMessage(), request.getRequestURI());
    }

    /**
     * exception 발생시 ResponseEntity 로 변환 후 반환
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
