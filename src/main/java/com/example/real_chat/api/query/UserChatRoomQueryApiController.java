package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.userChatRoom.response.GetChatRoomResponse;
import com.example.real_chat.dto.userChatRoom.response.GetUserChatRoomResponse;
import com.example.real_chat.dto.userChatRoom.response.GetUserReseponse;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import com.example.real_chat.service.query.UserChatRoomQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/query/user-chat-room")
public class UserChatRoomQueryApiController {

    private final UserChatRoomQueryService userChatRoomQueryService;

    @GetMapping("/{userId}/{roomId}")
    public ResponseEntity<GetUserChatRoomResponse> getUserChatRoom(
        @PathVariable Long userId,
        @PathVariable Long roomId
    ) {
        UserChatRoom userChatRoom = userChatRoomQueryService.getUserInChatRoom(userId, roomId);

        GetUserReseponse getUserReseponse = new GetUserReseponse(userChatRoom);
        GetChatRoomResponse getChatRoomResponse = new GetChatRoomResponse(userChatRoom);

        return ResponseEntity.ok(
                new GetUserChatRoomResponse(userChatRoom.getId(), getUserReseponse, getChatRoomResponse)
        );
    }

    @GetMapping("/user/chat-room-list/{userId}")
    public ResponseEntity<Result<List<GetChatRoomResponse>>> getChatRoomsUserParticipatesIn(
            @PathVariable Long userId
    ) {
        List<UserChatRoom> chatRoomList = userChatRoomQueryService.getChatRoomsUserParticipatesIn(userId);

        List<GetChatRoomResponse> response = chatRoomList.stream()
                .map(GetChatRoomResponse::new)
                .toList();

        return  ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/chat/user-list/{userId}")
    public ResponseEntity<Result<List<GetUserReseponse>>> getParticipantsInChatRoom(
            @PathVariable Long userId
    ) {
        List<UserChatRoom> userList = userChatRoomQueryService.getParticipantsInChatRoom(userId);

        List<GetUserReseponse> response = userList.stream()
                .map(GetUserReseponse::new)
                .toList();

        return ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/{userId}/{roomId}")
    public ResponseEntity<Result<Boolean>> doseUserExist(
            @PathVariable Long userId,
            @PathVariable Long roomId
    ) {
        Boolean doesUserExistInChatRoom = userChatRoomQueryService.doesUserExistInChatRoom(userId, roomId);
        return ResponseEntity.ok(new Result<>(doesUserExistInChatRoom));
    }
}