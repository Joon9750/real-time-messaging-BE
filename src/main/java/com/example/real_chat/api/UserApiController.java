package com.example.real_chat.api;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.user.request.CreateUserRequestDto;
import com.example.real_chat.dto.user.response.CreateUserResponseDto;
import com.example.real_chat.dto.user.response.GetUserResponseDto;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<CreateUserResponseDto> createUser(
            @RequestBody CreateUserRequestDto request
    ) {
        User user = User.create(request.getUserName());
        Long userId = userService.addUser(user);

        return ResponseEntity.ok().body(new CreateUserResponseDto(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.ok(CommonApiResult.createOk("유저가 정상적으로 삭제되었습니다."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> getUserById(
            @PathVariable Long id
    ) {
       User user = userService.getUserById(id);
       return ResponseEntity.ok().body(new GetUserResponseDto(user.getId(), user.getUserName()));
    }

    @GetMapping("/all")
    public ResponseEntity<Result<?>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<GetUserResponseDto> response = users.stream()
                .map(m -> new GetUserResponseDto(m.getId(), m.getUserName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<?>> getUndeletedUsers() {
        List<User> users = userService.getUndeletedUsers();
        List<GetUserResponseDto> response = users.stream()
                .map(m -> new GetUserResponseDto(m.getId(), m.getUserName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}
