package com.example.real_chat.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int code;
    private final String message;
    private final LocalDateTime timestamp;

    private List<ValidationError> validationErrors;

    @Data
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    // ValidationException이 발생했을 시, Request 데이터의 Validation에 실패한 리스트들을 모두 넣는다.
    public void addValidationError(String field, String message){
        if(Objects.isNull(validationErrors)){
            validationErrors = new ArrayList<ValidationError>();
        }
        validationErrors.add(new ValidationError(field, message));
    }
}
