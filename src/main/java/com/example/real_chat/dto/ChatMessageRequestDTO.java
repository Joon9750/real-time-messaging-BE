package com.example.real_chat.dto;

import com.example.real_chat.entity.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequestDTO<T> {

    private Long roomId;
    private String sender;
    private Resource<T> data;
}
