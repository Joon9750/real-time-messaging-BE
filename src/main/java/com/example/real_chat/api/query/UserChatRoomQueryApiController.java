package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.userchatroom.response.GetChatRoomResponse;
import com.example.real_chat.dto.userchatroom.response.GetUserChatRoomResponse;
import com.example.real_chat.dto.userchatroom.response.GetUserReseponse;
import com.example.real_chat.entity.userchatroom.UserChatRoom;
import com.example.real_chat.service.query.UserChatRoomQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/query/user-chat-room")
@Tag(name = "UserChatRoom")
public class UserChatRoomQueryApiController {

    private final UserChatRoomQueryService userChatRoomQueryService;

    @GetMapping()
    @Operation(
            summary = "채팅방과 사용자의 연결테이블 조회",
            description = "채팅방과 사용자의 연결테이블인 UserChatRoom의 id를 조회합니다.")
    public ResponseEntity<GetUserChatRoomResponse> getUserChatRoom(
            @RequestParam Long userId,
            @RequestParam Long roomId
    ) {
        UserChatRoom userChatRoom = userChatRoomQueryService.getUserInChatRoom(userId, roomId);

        GetUserReseponse getUserReseponse = new GetUserReseponse(userChatRoom);
        GetChatRoomResponse getChatRoomResponse = new GetChatRoomResponse(userChatRoom);

        return ResponseEntity.ok(
                new GetUserChatRoomResponse(userChatRoom.getId(), getUserReseponse, getChatRoomResponse)
        );
    }

    @GetMapping("/user/{userId}/chat-rooms")
    @Operation(summary = "사용자가 속한 채팅방 모두 조회", description = "특정 사용자가 속한 채팅방을 모두 조회합니다.")
    public ResponseEntity<Result<List<GetChatRoomResponse>>> getChatRoomsUserParticipatesIn(
            @PathVariable Long userId
    ) {
        List<UserChatRoom> chatRoomList = userChatRoomQueryService.getChatRoomsUserParticipatesIn(userId);

        List<GetChatRoomResponse> response = chatRoomList.stream()
                .map(GetChatRoomResponse::new)
                .toList();

        return  ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/chat-room/{roomId}/participants")
    @Operation(summary = "채팅방에 속한 사용자 모두 조회", description = "특정 채팅방에 속한 사용자를 모두 조회합니다.")
    public ResponseEntity<Result<List<GetUserReseponse>>> getParticipantsInChatRoom(
            @PathVariable Long roomId
    ) {
        List<UserChatRoom> userList = userChatRoomQueryService.getParticipantsInChatRoom(roomId);

        List<GetUserReseponse> response = userList.stream()
                .map(GetUserReseponse::new)
                .toList();

        return ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/user/{userId}/chat-room/{roomId}/exists")
    @Operation(summary = "채팅방에 사용자가 있는지 확인", description = "특정 채팅방에 특정 사용자가 있는지 확인합니다.")
    public ResponseEntity<Result<Boolean>> doesUserExist(
            @PathVariable Long userId,
            @PathVariable Long roomId
    ) {
        Boolean doesUserExistInChatRoom = userChatRoomQueryService.doesUserExistInChatRoom(userId, roomId);
        return ResponseEntity.ok(new Result<>(doesUserExistInChatRoom));
    }
}