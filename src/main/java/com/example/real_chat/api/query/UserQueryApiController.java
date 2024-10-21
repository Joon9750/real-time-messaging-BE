package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.user.response.GetUserResponseDto;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/query/user")
public class UserQueryApiController {

    private final UserQueryService userQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> getUserById(
            @PathVariable Long id
    ) {
        User user = userQueryService.getUserById(id);
        return ResponseEntity.ok().body(new GetUserResponseDto(user.getId(), user.getUserName()));
    }

    @GetMapping("/all")
    public ResponseEntity<Result<?>> getAllUsers() {
        List<User> users = userQueryService.getAllUsers();
        List<GetUserResponseDto> response = users.stream()
                .map(m -> new GetUserResponseDto(m.getId(), m.getUserName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<?>> getUndeletedUsers() {
        List<User> users = userQueryService.getUndeletedUsers();
        List<GetUserResponseDto> response = users.stream()
                .map(m -> new GetUserResponseDto(m.getId(), m.getUserName()))
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}
