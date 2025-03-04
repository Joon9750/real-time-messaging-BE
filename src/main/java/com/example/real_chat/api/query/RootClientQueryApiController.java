package com.example.real_chat.api.query;

import com.example.real_chat.dto.rootclient.response.GetRootClientResponse;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.service.query.RootClientQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/query/root-client")
@Tag(name = "Root client")
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
