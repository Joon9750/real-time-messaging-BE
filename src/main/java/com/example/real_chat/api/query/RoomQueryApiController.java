package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.room.response.RoomResponse;
import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.service.query.RoomQueryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/query/room")
public class RoomQueryApiController {

    private final RoomQueryService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoom(
            @PathVariable Long id
    ) {
        ChatRoom chatRoom = roomService.getRoom(id);
        return ResponseEntity.ok().body(new RoomResponse(id, chatRoom.getName()));
    }

    @GetMapping()
    public ResponseEntity<Result<List<RoomResponse>>> getAllRooms() {
        List<ChatRoom> chatRooms = roomService.getAllRooms();
        List<RoomResponse> response = chatRooms.stream()
                .map(m -> new RoomResponse(m.getId(), m.getName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<List<RoomResponse>>> getUnDeleteRooms() {
        List<ChatRoom> unDeletedRooms = roomService.getUnDeletedRooms();
        List<RoomResponse> response = unDeletedRooms.stream()
                .map(m -> new RoomResponse(m.getId(), m.getName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}