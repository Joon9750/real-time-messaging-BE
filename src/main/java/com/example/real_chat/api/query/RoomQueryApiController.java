package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.room.response.RoomResponseDTO;
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
    public ResponseEntity<RoomResponseDTO> getRoom(
            @PathVariable Long id
    ) {
        ChatRoom chatRoom = roomService.getRoom(id);
        return ResponseEntity.ok().body(new RoomResponseDTO(id, chatRoom.getName()));
    }

    @GetMapping()
    public ResponseEntity<Result<?>> getAllRooms() {
        List<ChatRoom> chatRooms = roomService.getAllRooms();
        return ResponseEntity.ok().body(new Result<>(chatRooms));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<?>> getUnDeleteRooms() {
        List<ChatRoom> unDeletedRooms = roomService.getUnDeletedRooms();
        return ResponseEntity.ok().body(new Result<>(unDeletedRooms));
    }
}