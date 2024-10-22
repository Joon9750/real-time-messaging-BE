package com.example.real_chat.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final int code;
    private final String message;
    private final String requestUrl;
    private final LocalDateTime timestamp;
}
