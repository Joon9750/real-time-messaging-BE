package com.example.real_chat.api.query;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.rootClient.request.UpdateRootClientRequestDto;
import com.example.real_chat.dto.rootClient.response.GetRootClientResponseDto;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.service.query.RootClientQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/root-client/query")
public class RootClientQueryApiController {

    private final RootClientQueryService rootClientQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<GetRootClientResponseDto> getRootClient(
            @PathVariable Long id
    ) {
        RootClient rootClient = rootClientQueryService.getRootClient(id);

        return ResponseEntity.ok().body(new GetRootClientResponseDto(rootClient));
    }
}
