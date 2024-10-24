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

    @GetMapping()
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
    public ResponseEntity<Result<List<GetUserReseponse>>> getParticipantsInChatRoom(
            @PathVariable Long userId
    ) {
        List<UserChatRoom> userList = userChatRoomQueryService.getParticipantsInChatRoom(userId);

        List<GetUserReseponse> response = userList.stream()
                .map(GetUserReseponse::new)
                .toList();

        return ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/user/{userId}/chat-room/{roomId}/exists")
    public ResponseEntity<Result<Boolean>> doesUserExist(
            @PathVariable Long userId,
            @PathVariable Long roomId
    ) {
        Boolean doesUserExistInChatRoom = userChatRoomQueryService.doesUserExistInChatRoom(userId, roomId);
        return ResponseEntity.ok(new Result<>(doesUserExistInChatRoom));
    }
}