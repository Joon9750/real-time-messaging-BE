package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.rootclient.request.CreateRootClientRequest;
import com.example.real_chat.dto.rootclient.request.UpdateRootClientRequest;
import com.example.real_chat.dto.rootclient.response.CreateRootClientResponse;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.service.command.RootClientCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/command/root-client")
public class RootClientCommandApiController {

    private final RootClientCommandService rootClientCommandService;

    @PostMapping()
    public ResponseEntity<CreateRootClientResponse> createRootClient(
            @RequestBody @Valid CreateRootClientRequest requestDto
    ) {
        Long rootClientId = rootClientCommandService.addRootClient(
                RootClient.createRootClient(
                        requestDto.getRootClientId(),
                        requestDto.getRootClientPassword(),
                        requestDto.getRootClientName()
                )
        );

        return ResponseEntity.ok().body(new CreateRootClientResponse(rootClientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteRootClient(
            @PathVariable Long id
    ) {
        rootClientCommandService.deleteRootClient(id);
        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 삭제 되었습니다."));
    }

    // clientId, clientPassword, clientName 중 변경 소요가 없는 경우 null 또는 "" 빈 문자열로 넣어주시면 됩니다.
    @PatchMapping("/{id}")
    public ResponseEntity<CommonApiResult> updateRootClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRootClientRequest requestDto
    ) {
        rootClientCommandService.updateRootClient(
                id,
                requestDto.getRootClientId(),
                requestDto.getRootClientPassword(),
                requestDto.getRootClientName()
        );

        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 수정 되었습니다."));
    }
}
