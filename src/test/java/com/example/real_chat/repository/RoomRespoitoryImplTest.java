package com.example.real_chat.repository;

import com.example.real_chat.entity.ChatRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class RoomRespoitoryImplTest {

    @Autowired RoomRespoitoryImpl roomRespoitory;

    private ChatRoom chatRoom1;
    private ChatRoom chatRoom2;

    @BeforeEach
    void setUp() {
        // given
        this.chatRoom1 = ChatRoom.createRoom("chatRoom1");
        this.chatRoom2 = ChatRoom.createRoom("chatRoom2");

        // when
        roomRespoitory.save(chatRoom1);
        roomRespoitory.save(chatRoom2);
    }

    @Test
    void saveAndFindById() {
        // when
        ChatRoom chatRoom1 = roomRespoitory.findById(this.chatRoom1.getId());
        ChatRoom chatRoom2 = roomRespoitory.findById(this.chatRoom2.getId());

        // then
        assertEquals(chatRoom1.getId(), this.chatRoom1.getId());
        assertEquals(chatRoom2.getId(), this.chatRoom2.getId());
    }

    @Test
    @DisplayName("데이터를 삭제하면 DB에서 완전히 삭제되지 않고 isdeleted 필드가 true로 됩니다. DB에서 지워지지 않았기 때문에 findById로 조회됩니다.")
    void delete() {
        // when
        roomRespoitory.delete(chatRoom1.getId());
        ChatRoom chatRoom1 = roomRespoitory.findById(this.chatRoom1.getId());

        // then
        assertTrue(chatRoom1.isDeleted());
        assertFalse(chatRoom2.isDeleted());
        assertEquals(chatRoom1.getId(), this.chatRoom1.getId());
    }

    @Test
    void findAll() {
        // when
        List<ChatRoom> chatRooms = roomRespoitory.findAll();

        // then
        assertThat(chatRooms).hasSize(2);
    }
}