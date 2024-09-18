package com.example.real_chat.controller;

import com.example.real_chat.dto.room.request.CreateRoomRequestDTO;
import com.example.real_chat.dto.room.response.CreateRoomResponseDTO;
import com.example.real_chat.dto.room.response.RoomResponseDTO;
import com.example.real_chat.entity.Room;
import com.example.real_chat.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping() // localhost:8080/api/v1/room
    public ResponseEntity<CreateRoomResponseDTO> saveRoom(
            @RequestBody CreateRoomRequestDTO createRoomRequestDTO
    ) {
        Room room = Room.createRoom(createRoomRequestDTO.getName());
        Long roomId = roomService.addRoom(room);
        return ResponseEntity.ok().body(new CreateRoomResponseDTO(roomId));
    }

    @GetMapping("/{id}") // localhost:8080/api/v1/room/1
    public ResponseEntity<RoomResponseDTO> getRoom(
            @PathVariable Long id
    ) {
        Room room = roomService.getRoom(id);
        return ResponseEntity.ok().body(RoomResponseDTO.from(room));
    }

    @GetMapping() // localhost:8080/api/v1/room
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponseDTO> collect =  rooms.stream()
                .filter(m -> !m.isDeleted())
                .map(m-> RoomResponseDTO.from(m))
                .toList();
        return ResponseEntity.ok().body(collect);
    }

    @DeleteMapping("/{id}") // localhost:8080/api/v1/room/1
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity deleteRoom(
            @PathVariable Long id
    ) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok().build();
    }
}