package omg.wecan.chatting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.chatting.dto.ChatRoom;
import omg.wecan.chatting.dto.ChattingRoomRequest;
import omg.wecan.chatting.service.ChatService;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatService chatService;

    // 채팅방 정보 조회
    @GetMapping()
    public ResponseEntity<ApiResponse<ChatRoom>> goChatRoom(@RequestParam String roomId){
        ChatRoom chatRoom = chatService.findRoomById(roomId);
        return ResponseEntity.ok(ApiResponse.success(chatRoom));
    }

    // 채팅방 생성
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createRoom(@RequestBody ChattingRoomRequest chattingRoomRequest) {
        ChatRoom room = chatService.createChatRoom(chattingRoomRequest.getChallengeId());
        return ResponseEntity.ok(ApiResponse.success(room.getRoomId()));
    }
}
