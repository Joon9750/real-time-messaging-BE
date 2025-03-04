package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.user.response.GetUserResponse;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.query.UserQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User")
public class UserQueryApiController {

    private final UserQueryService userQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(
            @PathVariable Long id
    ) {
        User user = userQueryService.getUserById(id);
        return ResponseEntity.ok().body(new GetUserResponse(user));
    }

    @GetMapping("/{rootClientId}/all")
    public ResponseEntity<Result<List<GetUserResponse>>> getRootClientUsers(
            @PathVariable Long rootClientId
    ) {
        List<User> userList = userQueryService.getRootClientUsers(rootClientId);
        List<GetUserResponse> response = userList.stream()
                .map(GetUserResponse::new)
                .toList();

        return ResponseEntity.ok().body(new Result<>(response));
    }
}
