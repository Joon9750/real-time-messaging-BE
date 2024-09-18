package com.example.real_chat.repository;

import com.example.real_chat.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRespoitoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(Room room) {
        entityManager.persist(room);
        return room.getId();
    }

    @Override
    public Room findById(Long id) {
        return entityManager.find(Room.class, id);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        Room room = entityManager.find(Room.class, id);
        if (room.isDeleted()) throw new RuntimeException();
        else {
            room.delete();
        }
        // entityManager에서는 지우지 않고 isdeleted로만 판별
        // entityManager.remove(entityManager.find(Room.class, id));
    }

    @Override
    public List<Room> findAll() {
        return entityManager.createQuery("select m from Room m", Room.class)
                .getResultList();
    }
}
