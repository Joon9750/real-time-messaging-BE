package com.example.real_chat.api.command;

import com.example.real_chat.dto.userChatRoom.request.CreateUserChatRoomRequest;
import com.example.real_chat.dto.userChatRoom.response.CreateUserChatRoomResponse;
import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.command.UserChatRoomCommandService;
import com.example.real_chat.service.query.RoomQueryService;
import com.example.real_chat.service.query.UserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/command/user-chat-room")
public class UserChatRoomCommandApiController {

    private final UserChatRoomCommandService userChatRoomCommandService;

    private final UserQueryService userQueryService;
    private final RoomQueryService roomQueryService;

    // 채팅방을 만들고 유저를 만들 이후 채팅방에 유저가 들어갈 경우 호출되는 api
    // 이미 해당 유저가 채팅방에 속한 경우 RuntimeException 던짐
    @PostMapping()
    public ResponseEntity<CreateUserChatRoomResponse> createUserChatRoom(
            @RequestBody @Valid CreateUserChatRoomRequest request
    ) {
        User user = userQueryService.getUserById(request.getUserId());
        ChatRoom room = roomQueryService.getRoom(request.getRoomId());

        Long userChatRoomId = userChatRoomCommandService.joinChatRoom(user, room);
        return ResponseEntity.ok(new CreateUserChatRoomResponse(userChatRoomId));
    }
}
