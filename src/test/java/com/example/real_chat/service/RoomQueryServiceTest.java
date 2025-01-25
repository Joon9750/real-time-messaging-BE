package com.example.real_chat.service;

import com.example.real_chat.service.query.RoomQueryServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomQueryServiceTest {

    @InjectMocks
    private RoomQueryServiceImpl roomQueryService;
}
