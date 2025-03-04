package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.room.request.CreateRoomRequest;
import com.example.real_chat.dto.room.request.UpdateRoomRequest;
import com.example.real_chat.dto.room.response.CreateRoomResponse;
import com.example.real_chat.service.command.RoomCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/command/room")
@Tag(name = "Chat room", description = "채팅방 관련 API")
public class RoomCommandApiController {

    private final RoomCommandService roomService;

    @PostMapping()
    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성합니다.")
    public ResponseEntity<CreateRoomResponse> createRoom(
            @RequestBody @Valid CreateRoomRequest request
    ) {
        Long roomId = roomService.addRoom(request.getName(), request.getRootClientId());
        return ResponseEntity.ok().body(new CreateRoomResponse(roomId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "채팅방 삭제", description = "채팅방을 삭제합니다.")
    public ResponseEntity<CommonApiResult> deleteRoom(
            @PathVariable Long id
    ) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(CommonApiResult.createOk(
                "채팅방이 정상적으로 삭제 되었고, 채팅방에 속한 사용자들 모두 채팅방에서 나가졌습니다."
        ));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "채팅방 이름 수정", description = "기존 채팅방 이름을 수정합니다.")
    public ResponseEntity<CommonApiResult> updateRoom(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRoomRequest requestDto
    ) {
        roomService.updateChatRoom(id, requestDto.getName());
        return ResponseEntity.ok(CommonApiResult.createOk("채팅방 이름이 정상적으로 변경되었습니다."));
    }
}
