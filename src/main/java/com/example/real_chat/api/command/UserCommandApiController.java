package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.user.request.CreateUserRequest;
import com.example.real_chat.dto.user.response.CreateUserResponse;
import com.example.real_chat.service.command.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/command/user")
@Tag(name = "User", description = "사용자 관련 API")
public class UserCommandApiController {

    private final UserCommandService userCommandService;

    @PostMapping()
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody @Valid CreateUserRequest request
    ) {
        Long userId = userCommandService.addUser(request.getUserName(), request.getRootClientId());
        return ResponseEntity.ok().body(new CreateUserResponse(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 삭제", description = "사용자를 삭제하면, 해당 사용자가 속한 채팅방에서 유저가 나가집니다.")
    public ResponseEntity<CommonApiResult> deleteUser(
            @PathVariable Long id
    ) {
        userCommandService.delete(id);
        return ResponseEntity.ok(CommonApiResult.createOk(
                "유저가 정상적으로 삭제되었습니다. 추가로 유저가 속한 채팅방에서 유저가 나가졌습니다."
        ));
    }
}
