package com.example.real_chat.api.command;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.rootclient.request.CreateRootClientRequest;
import com.example.real_chat.dto.rootclient.request.UpdateRootClientRequest;
import com.example.real_chat.dto.rootclient.response.CreateRootClientResponse;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.service.command.RootClientCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/command/root-client")
@Tag(name = "Root client", description = "루트 클라이언트 관련 API, ")
public class RootClientCommandApiController {

    private final RootClientCommandService rootClientCommandService;

    @PostMapping()
    @Operation(summary = "루트 클라이언트 생성", description = "새로운 루트 클라이언트를 생성합니다.")
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
    @Operation(
            summary = "루트 클라이언트 삭제",
            description = "기존 루트 클라이언트를 삭제합니다. 루트 클라이언트 삭제 시 실제로 DB에서 삭제하지 않고 isDelete 인스턴스로 삭제 여부 표기합니다.")
    public ResponseEntity<CommonApiResult> deleteRootClient(
            @PathVariable Long id
    ) {
        rootClientCommandService.deleteRootClient(id);
        return ResponseEntity.ok(CommonApiResult.createOk("루트 클라이언트가 정상적으로 삭제 되었습니다."));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "루트 클라이언트 수정",
            description = "clientId, clientPassword, clientName 중 변경 소요가 없는 경우 null 또는 \"\" 빈 문자열로 넣어주시면 됩니다.")
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
