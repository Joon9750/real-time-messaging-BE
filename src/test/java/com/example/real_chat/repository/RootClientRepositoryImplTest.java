package com.example.real_chat.repository;

import com.example.real_chat.entity.ChatRoom;
import com.example.real_chat.entity.RootClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RootClientRepositoryImplTest {

    @Autowired RootClientRepository rootClientRepository;

    private RootClient rootClient1;
    private RootClient rootClient2;

    @BeforeEach
    void setUp() {
        // given
        this.rootClient1 = RootClient.createRootClient("1", "11111", "hong");
        this.rootClient2 = RootClient.createRootClient("2", "22222", "kim");

        rootClientRepository.save(rootClient1);
        rootClientRepository.save(rootClient2);
    }

    @Test
    void saveAndFindById() {
        // when
        RootClient rootClient1 = rootClientRepository.findById(this.rootClient1.getId()).orElseThrow();
        RootClient rootClient2 = rootClientRepository.findById(this.rootClient2.getId()).orElseThrow();

        // then
        assertEquals(this.rootClient1.getId(), rootClient1.getId());
        assertEquals(this.rootClient2.getId(), rootClient2.getId());
    }

    @Test
    @DisplayName("데이터를 삭제하면 DB에서 완전히 삭제되지 않고 isdeleted 필드가 true로 된다.")
    void delete() {
        // when
        rootClientRepository.deleteById(rootClient1.getId());
        RootClient rootClient1 = rootClientRepository.findById(this.rootClient1.getId()).orElseThrow();

        // then
        assertTrue(rootClient1.isDeleted());
        assertFalse(rootClient2.isDeleted());
    }

    @Test
    @DisplayName("데이터를 삭제해도 DB에서 완전히 지워지지 않아서 getId로 조회 가능하다.")
    void getIdAfterDelete() {
        // when
        rootClientRepository.deleteById(rootClient1.getId());
        RootClient rootClient1 = rootClientRepository.findById(this.rootClient1.getId()).orElseThrow();

        // then
        assertEquals(rootClient1.getId(), this.rootClient1.getId());
    }

    @Test
    @DisplayName("데이터가 삭제되면 삭제된 시간이 자동으로 갱신된다.")
    void checkDeleteTimeAfterDelete() {
        // when
        rootClientRepository.deleteById(rootClient1.getId());

        // then
        assertNotNull(rootClientRepository.findById(this.rootClient1.getId()).orElseThrow().getDeletedAt());
        assertNull(rootClientRepository.findById(this.rootClient2.getId()).orElseThrow().getDeletedAt());
    }

    @Test()
    @DisplayName("이미 delete 처리된 데이터를 다시 delete하면 RuntimeException 던진다.")
    void checkDeleteMethodRuntimeException() {
        // when
        rootClientRepository.deleteById(rootClient1.getId());

        // then
        assertThrows(RuntimeException.class, () -> rootClientRepository.deleteById(rootClient1.getId()));
    }
}