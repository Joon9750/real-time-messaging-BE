package com.example.real_chat.exception.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final int code;
    private final String message;
    private final String requestUrl;
    private final LocalDateTime timestamp;
}
