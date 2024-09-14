package com.example.real_chat.service;

import com.example.real_chat.entity.Room;
import com.example.real_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Long addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.delete(roomId);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
