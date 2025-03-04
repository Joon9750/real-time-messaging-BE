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
        return ResponseEntity.ok().body(new RoomResponse(chatRoom));
    }

    @GetMapping("/{rootClientId}/all")
    public ResponseEntity<Result<List<RoomResponse>>> getAllRooms(
            @PathVariable Long rootClientId
    ) {
        List<ChatRoom> chatRooms = roomService.getRootClientRooms(rootClientId);
        List<RoomResponse> response = chatRooms.stream()
                .map(RoomResponse::new)
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}