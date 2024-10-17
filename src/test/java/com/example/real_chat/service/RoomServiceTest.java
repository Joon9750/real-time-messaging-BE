package com.example.real_chat.service;

import com.example.real_chat.entity.room.ChatRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired private RoomService roomService;

    private ChatRoom chatRoom1;
    private ChatRoom chatRoom2;

    @BeforeEach
    void setUp() {
        // given
        this.chatRoom1 = ChatRoom.createRoom("firstRoom");
        this.chatRoom2 = ChatRoom.createRoom("secondRoom");

        roomService.addRoom(chatRoom1);
        roomService.addRoom(chatRoom2);
    }

    @Test
    void addAndGetRoom() {
        // then
        assertThat(roomService.getRoom(chatRoom1.getId())).isEqualTo(chatRoom1);
        assertThat(roomService.getRoom(chatRoom2.getId())).isEqualTo(chatRoom2);
    }

    @Test
    void deleteRoom() {
        // when
        roomService.deleteRoom(chatRoom1.getId());

        // then
        assertTrue(chatRoom1.isDeleted());
        assertFalse(chatRoom2.isDeleted());
        assertNotNull(roomService.getRoom(chatRoom1.getId()).getDeletedAt());
        assertThat(roomService.getRoom(chatRoom1.getId()).getId()).isEqualTo(chatRoom1.getId());
    }

    @Test
    void getAllRooms() {
        // when
        roomService.deleteRoom(chatRoom1.getId());
        ChatRoom chatRoom3 = ChatRoom.createRoom("thirdRoom");
        roomService.addRoom(chatRoom3);

        // then
        List<ChatRoom> rooms = roomService.getAllRooms();
        assertThat(rooms.size()).isEqualTo(3);
    }

    @Test
    void getUnDeletedRooms() {
        // when
        roomService.deleteRoom(chatRoom1.getId());

        // then
        List<ChatRoom> rooms = roomService.getUnDeletedRooms();
        assertThat(rooms.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("채팅방 이름이 수정되어야 성공")
    void updateChatRoom() {
        // when
        roomService.updateChatRoom(this.chatRoom1.getId(), "newFirstRoom");

        // then
        assertThat(this.chatRoom1.getName()).isEqualTo("newFirstRoom");
        assertThat(this.chatRoom2.getName()).isEqualTo("secondRoom");
    }
}