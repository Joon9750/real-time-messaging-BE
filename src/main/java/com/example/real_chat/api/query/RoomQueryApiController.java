package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.room.response.RoomResponse;
import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.service.query.RoomQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/query/room")
@Tag(name = "Chat room")
public class RoomQueryApiController {

    private final RoomQueryService roomService;

    @GetMapping("/{id}")
    @Operation(summary = "채팅방 조회", description = "채팅방 생성에서 얻은 채팅방 id로 단일 채팅방을 조회합니다.")
    public ResponseEntity<RoomResponse> getRoom(
            @PathVariable Long id
    ) {
        ChatRoom chatRoom = roomService.getRoom(id);
        return ResponseEntity.ok().body(new RoomResponse(chatRoom));
    }

    @GetMapping("/{rootClientId}/all")
    @Operation(summary = "루트 클라이언트에 속한 모든 채팅방 조회")
    public ResponseEntity<Result<List<RoomResponse>>> getRootClientRooms(
            @PathVariable Long rootClientId
    ) {
        List<ChatRoom> chatRooms = roomService.getRootClientRooms(rootClientId);
        List<RoomResponse> response = chatRooms.stream()
                .map(RoomResponse::new)
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}