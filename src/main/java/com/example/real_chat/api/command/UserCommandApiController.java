package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.user.request.CreateUserRequest;
import com.example.real_chat.dto.user.response.CreateUserResponse;
import com.example.real_chat.service.command.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/command/user")
public class UserCommandApiController {

    private final UserCommandService userCommandService;

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest request
    ) {
        Long userId = userCommandService.addUser(request.getUserName(), request.getRootClientId());
        return ResponseEntity.ok().body(new CreateUserResponse(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteUser(
            @PathVariable Long id
    ) {
        userCommandService.deleteUser(id);
        return ResponseEntity.ok(CommonApiResult.createOk("유저가 정상적으로 삭제되었습니다."));
    }
}
