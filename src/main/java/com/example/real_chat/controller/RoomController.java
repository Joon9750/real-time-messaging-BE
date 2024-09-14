package com.example.real_chat.controller;

import com.example.real_chat.dto.CreateRoomRequestDTO;
import com.example.real_chat.dto.CreateRoomResponseDTO;
import com.example.real_chat.dto.RoomResponseDTO;
import com.example.real_chat.entity.Room;
import com.example.real_chat.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("api/v1/room") // localhost:8080/api/v1/room
    public ResponseEntity<CreateRoomResponseDTO> saveRoom(
            @RequestBody @Valid CreateRoomRequestDTO createRoomRequestDTO
    ) {
        Room room = Room.createRoom(createRoomRequestDTO.getName());
        Long roomId = roomService.addRoom(room);
        return ResponseEntity.ok().body(new CreateRoomResponseDTO(roomId));
    }

    @GetMapping("api/v1/room")
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponseDTO> collect =  rooms.stream()
                .filter(m -> m.isDeleted() == false)
                .map(m-> new RoomResponseDTO(m.getId(), m.getName()))
                .toList();
        return ResponseEntity.ok().body(collect);
    }
}
