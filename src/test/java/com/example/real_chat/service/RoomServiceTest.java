package com.example.real_chat.service;

import com.example.real_chat.entity.ChatRoom;
import org.junit.jupiter.api.BeforeEach;
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
    }

    @Test
    void getAllRooms() {
    }

    @Test
    void getUnDeletedRooms() {

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