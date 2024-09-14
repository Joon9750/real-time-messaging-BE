package com.example.real_chat.repository;

import com.example.real_chat.entity.Room;

import java.util.List;

public interface RoomRepository {

    Long save(Room room);

    Room findById(Long id);

    void delete(Long id);

    List<Room> findAll();
}
