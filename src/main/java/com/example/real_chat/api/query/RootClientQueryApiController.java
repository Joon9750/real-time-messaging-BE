package com.example.real_chat.api.query;

import com.example.real_chat.dto.rootClient.response.GetRootClientResponse;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.service.query.RootClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/query/root-client")
public class RootClientQueryApiController {

    private final RootClientQueryService rootClientQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetRootClientResponse> getRootClient(
            @PathVariable Long id
    ) {
        RootClient rootClient = rootClientQueryService.getRootClient(id);

        return ResponseEntity.ok().body(new GetRootClientResponse(rootClient));
    }
}
