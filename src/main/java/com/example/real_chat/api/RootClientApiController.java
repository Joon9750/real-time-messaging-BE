package com.example.real_chat.api;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.rootClient.request.CreateRootClientRequestDto;
import com.example.real_chat.dto.rootClient.request.UpdateRootClientRequestDto;
import com.example.real_chat.dto.rootClient.response.CreateRootClientResponseDto;
import com.example.real_chat.dto.rootClient.response.GetRootClientResponseDto;
import com.example.real_chat.entity.RootClient;
import com.example.real_chat.service.RootClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/root/client")
public class RootClientApiController {

    private final RootClientService rootClientService;

    @PostMapping()
    public ResponseEntity<CreateRootClientResponseDto> createRootClient(
            @RequestBody @Valid CreateRootClientRequestDto requestDto
    ) {
        Long rootClientId = rootClientService.addRootClient(
                RootClient.createRootClient(
                        requestDto.getRootClientId(),
                        requestDto.getRootClientPassword(),
                        requestDto.getRootClientName()
                )
        );

        return ResponseEntity.ok().body(new CreateRootClientResponseDto(rootClientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRootClientResponseDto> getRootClient(
            @PathVariable Long id
    ) {
        RootClient rootClient = rootClientService.getRootClient(id);

        return ResponseEntity.ok().body(new GetRootClientResponseDto(rootClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonApiResult> deleteRootClient(
            @PathVariable Long id
    ) {
        rootClientService.deleteRootClient(id);

        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 삭제 되었습니다."));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonApiResult> updateRootClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRootClientRequestDto requstDto
    ) {
        RootClient rootClient = rootClientService.getRootClient(id);
        rootClient.update(
                requstDto.getRootClientId(),
                requstDto.getRootClientPassword(),
                requstDto.getRootClientName()
        );

        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 수정 되었습니다."));
    }
}