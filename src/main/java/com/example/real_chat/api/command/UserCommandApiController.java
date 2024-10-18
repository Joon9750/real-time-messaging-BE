package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.user.request.CreateUserRequestDto;
import com.example.real_chat.dto.user.response.CreateUserResponseDto;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.command.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/command/user")
public class UserCommandApiController {

    private final UserCommandService userCommandService;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> createUser(
            @RequestBody CreateUserRequestDto request
    ) {
        User user = User.create(request.getUserName());
        Long userId = userCommandService.addUser(user);

        return ResponseEntity.ok().body(new CreateUserResponseDto(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteUser(
            @PathVariable Long id
    ) {
        userCommandService.deleteUser(id);
        return ResponseEntity.ok(CommonApiResult.createOk("유저가 정상적으로 삭제되었습니다."));
    }
}
