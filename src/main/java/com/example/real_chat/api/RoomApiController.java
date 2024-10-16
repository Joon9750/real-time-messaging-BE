package com.example.real_chat.api;

import com.example.real_chat.dto.common.CommonApiResult;
import com.example.real_chat.dto.common.Result;
import com.example.real_chat.dto.room.request.CreateRoomRequestDto;
import com.example.real_chat.dto.room.request.UpdateRoomRequestDto;
import com.example.real_chat.dto.room.response.CreateRoomResponseDTO;
import com.example.real_chat.dto.room.response.RoomResponseDTO;
import com.example.real_chat.entity.ChatRoom;
import com.example.real_chat.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/room")
public class RoomApiController {

    private final RoomService roomService;

    @PostMapping()
    public ResponseEntity<CreateRoomResponseDTO> createRoom(
            @RequestBody @Valid CreateRoomRequestDto createRoomRequestDTO
    ) {
        ChatRoom chatRoom = ChatRoom.createRoom(createRoomRequestDTO.getName());
        Long roomId = roomService.addRoom(chatRoom);

        return ResponseEntity.ok().body(new CreateRoomResponseDTO(roomId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoom(
            @PathVariable Long id
    ) {
        ChatRoom chatRoom = roomService.getRoom(id);

        return ResponseEntity.ok().body(RoomResponseDTO.from(chatRoom));
    }

    @GetMapping()
    public ResponseEntity<Result<List<RoomResponseDTO>>> getAllRooms() {
        List<ChatRoom> chatRooms = roomService.getAllRooms();

        return ResponseEntity.ok().body(new Result(chatRooms));
    }

    @GetMapping("/undeleted")
    public ResponseEntity<Result<List<RoomResponseDTO>>> getUnDeleteRooms() {
        List<ChatRoom> unDeletedRooms = roomService.getUnDeletedRooms();

        return ResponseEntity.ok().body(new Result(unDeletedRooms));
    }

    @DeleteMapping("/{id}")
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonApiResult> deleteRoom(
            @PathVariable Long id
    ) {
        roomService.deleteRoom(id);

        return ResponseEntity.ok(CommonApiResult.createOk("채팅방이 정상적으로 삭제 되었습니다."));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonApiResult> updateRoom(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRoomRequestDto requestDto
    ) {
        roomService.updateChatRoom(id, requestDto.getName());

        return ResponseEntity.ok(CommonApiResult.createOk("채팅방 이름이 정상적으로 변경되었습니다."));
    }
}