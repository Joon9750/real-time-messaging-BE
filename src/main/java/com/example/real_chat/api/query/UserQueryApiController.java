package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.user.response.GetUserResponse;
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
    public ResponseEntity<GetUserResponse> getUserById(
            @PathVariable Long id
    ) {
        User user = userQueryService.getUserById(id);
        return ResponseEntity.ok().body(new GetUserResponse(user));
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<GetUserResponse>>> getAllUsers() {
        List<User> users = userQueryService.getAllUsers();
        List<GetUserResponse> response = users.stream()
                .map(GetUserResponse::new)
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<List<GetUserResponse>>> getUndeletedUsers() {
        List<User> users = userQueryService.getUndeletedUsers();
        List<GetUserResponse> response = users.stream()
                .map(GetUserResponse::new)
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}
