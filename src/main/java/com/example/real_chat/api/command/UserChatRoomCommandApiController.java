package com.example.real_chat.api.command;

import com.example.real_chat.dto.userchatroom.request.CreateUserChatRoomRequest;
import com.example.real_chat.dto.userchatroom.response.CreateUserChatRoomResponse;
import com.example.real_chat.service.command.UserChatRoomCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/command/user-chat-room")
@Tag(name = "UserChatRoom", description = "유저와 채팅방 연결테이블 관련 API")
public class UserChatRoomCommandApiController {

    private final UserChatRoomCommandService userChatRoomCommandService;

    // 이미 해당 유저가 해당 채팅방에 속한 경우 CannotJoinChatRoomException 던짐
    @PostMapping()
    @Operation(summary = "채팅방에 유저 추가")
    public ResponseEntity<CreateUserChatRoomResponse> createUserChatRoom(
            @RequestBody @Valid CreateUserChatRoomRequest request
    ) {
        Long userChatRoomId = userChatRoomCommandService.joinChatRoom(request.getUserId(), request.getRoomId());
        return ResponseEntity.ok(new CreateUserChatRoomResponse(userChatRoomId));
    }
}