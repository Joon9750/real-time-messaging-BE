package com.example.real_chat.controller;

import com.example.real_chat.dto.Result;
import com.example.real_chat.dto.room.request.CreateRoomRequestDTO;
import com.example.real_chat.dto.room.response.CreateRoomResponseDTO;
import com.example.real_chat.dto.room.response.RoomResponseDTO;
import com.example.real_chat.entity.ChatRoom;
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
        ChatRoom chatRoom = ChatRoom.createRoom(createRoomRequestDTO.getName());
        Long roomId = roomService.addRoom(chatRoom);
        return ResponseEntity.ok().body(new CreateRoomResponseDTO(roomId));
    }

    @GetMapping("/{id}") // localhost:8080/api/v1/room/1
    public ResponseEntity<RoomResponseDTO> getRoom(
            @PathVariable Long id
    ) {
        ChatRoom chatRoom = roomService.getRoom(id);
        return ResponseEntity.ok().body(RoomResponseDTO.from(chatRoom));
    }

    @GetMapping() // localhost:8080/api/v1/room
    public ResponseEntity<Result<List<RoomResponseDTO>>> getAllRooms() {
        List<ChatRoom> chatRooms = roomService.getAllRooms();
        List<RoomResponseDTO> collect =  chatRooms.stream()
                .filter(m -> !m.isDeleted())
                .map(m-> RoomResponseDTO.from(m))
                .toList();
        return ResponseEntity.ok().body(new Result(collect));
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