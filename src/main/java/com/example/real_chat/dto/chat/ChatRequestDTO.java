package com.example.real_chat.dto.chat;

import com.example.real_chat.entity.chat.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequestDTO<T> {

    private Long roomId;
    private String sender;
    private Resource<T> data;
}
