package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.userChatRoom.request.GetChatRoomsRequest;
import com.example.real_chat.dto.userChatRoom.request.GetUserChatRoomRequest;
import com.example.real_chat.dto.userChatRoom.request.GetUserExistRequest;
import com.example.real_chat.dto.userChatRoom.response.GetChatRoomResponse;
import com.example.real_chat.dto.userChatRoom.response.GetUserChatRoomResponse;
import com.example.real_chat.dto.userChatRoom.response.GetUserReseponse;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import com.example.real_chat.service.query.UserChatRoomQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/query/user-chat-room")
public class UserChatRoomQueryApiController {

    private final UserChatRoomQueryService userChatRoomQueryService;

    @GetMapping()
    public ResponseEntity<GetUserChatRoomResponse> getUserChatRoom(
            @RequestBody @Valid GetUserChatRoomRequest request
    ) {
        UserChatRoom userChatRoom = userChatRoomQueryService.getUserInChatRoom(request.getUserId(), request.getRoomId());
        GetUserReseponse getUserReseponse = new GetUserReseponse(userChatRoom.getUser().getId(), userChatRoom.getChatRoom().getName());
        GetChatRoomResponse getChatRoomResponse = new GetChatRoomResponse(userChatRoom.getChatRoom().getId(), userChatRoom.getChatRoom().getName());
        return ResponseEntity.ok(
                new GetUserChatRoomResponse(userChatRoom.getId(), getUserReseponse, getChatRoomResponse)
        );
    }

    @GetMapping("/user/chat-room-list")
    public ResponseEntity<Result<List<GetChatRoomResponse>>> getChatRoomsUserParticipatesIn(
            @RequestBody @Valid GetChatRoomsRequest request
    ) {
        List<UserChatRoom> chatRoomList = userChatRoomQueryService.getChatRoomsUserParticipatesIn(request.getUserId());
        List<GetChatRoomResponse> response = chatRoomList.stream()
                .map(m -> new GetChatRoomResponse(m.getChatRoom().getId(), m.getChatRoom().getName()))
                .toList();
        return  ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/chat/user-list")
    public ResponseEntity<Result<List<GetUserReseponse>>> getParticipantsInChatRoom(
            @RequestBody @Valid GetChatRoomsRequest request
    ) {
        List<UserChatRoom> userList = userChatRoomQueryService.getParticipantsInChatRoom(request.getUserId());
        List<GetUserReseponse> response = userList.stream()
                .map(m -> new GetUserReseponse(m.getUser().getId(), m.getUser().getUserName()))
                .toList();
        return ResponseEntity.ok(new Result<>(response));
    }

    @GetMapping("/dose-user-exist")
    public ResponseEntity<Result<Boolean>> doseUserExist(
            @RequestBody @Valid GetUserExistRequest request
    ) {
        Boolean doesUserExistInChatRoom = userChatRoomQueryService.doesUserExistInChatRoom(request.getUserId(), request.getRoomId());
        return ResponseEntity.ok(new Result<>(doesUserExistInChatRoom));
    }
}