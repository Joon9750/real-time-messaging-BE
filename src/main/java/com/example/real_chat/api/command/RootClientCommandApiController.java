package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.rootClient.request.CreateRootClientRequestDto;
import com.example.real_chat.dto.rootClient.request.UpdateRootClientRequestDto;
import com.example.real_chat.dto.rootClient.response.CreateRootClientResponseDto;
import com.example.real_chat.entity.rootClient.RootClient;
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
    public ResponseEntity<CreateRootClientResponseDto> createRootClient(
            @RequestBody @Valid CreateRootClientRequestDto requestDto
    ) {
        Long rootClientId = rootClientCommandService.addRootClient(
                RootClient.createRootClient(
                        requestDto.getRootClientId(),
                        requestDto.getRootClientPassword(),
                        requestDto.getRootClientName()
                )
        );

        return ResponseEntity.ok().body(new CreateRootClientResponseDto(rootClientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteRootClient(
            @PathVariable Long id
    ) {
        rootClientCommandService.deleteRootClient(id);

        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 삭제 되었습니다."));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonApiResult> updateRootClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRootClientRequestDto requestDto
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
