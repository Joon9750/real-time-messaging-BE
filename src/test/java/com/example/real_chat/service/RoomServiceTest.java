package com.example.real_chat.service;

import com.example.real_chat.entity.ChatRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired private RoomService roomService;

    @Test
    void addRoom() {
    }

    @Test
    void getRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void getAllRooms() {
    }

    @Test
    @DisplayName("채팅방 이름이 수정되어야 성공")
    void updateChatRoom() {
        // given
        ChatRoom chatRoom1 = ChatRoom.createRoom("firstRoom");
        ChatRoom chatRoom2 = ChatRoom.createRoom("secondRoom");
        roomService.addRoom(chatRoom1);
        roomService.addRoom(chatRoom2);

        // when
        roomService.updateChatRoom(chatRoom1.getId(), "newFirstRoom");
        roomService.updateChatRoom(chatRoom2.getId(), "newSecondRoom");

        // then
        assertThat(chatRoom1.getName()).isEqualTo("newFirstRoom");
        assertThat(chatRoom2.getName()).isEqualTo("newSecondRoom");
    }
}