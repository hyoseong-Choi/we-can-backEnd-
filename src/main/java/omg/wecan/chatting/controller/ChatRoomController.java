package omg.wecan.chatting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.chatting.dto.ChatDto;
import omg.wecan.chatting.dto.ChatRoomDto;
import omg.wecan.chatting.dto.ChattingRoomRequest;
import omg.wecan.chatting.dto.ChattingUserDto;
import omg.wecan.chatting.service.ChatService;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomController {

    private final ChatService chatService;

    // 채팅방 생성
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> createRoom(@RequestBody ChattingRoomRequest chattingRoomRequest) {
        ChatRoomDto room = chatService.createChatRoom(chattingRoomRequest.getChallengeId());
        return ResponseEntity.ok(ApiResponse.success(room.getRoomId()));
    }

    // 채팅방 나가기
    @DeleteMapping("/")
    public ResponseEntity<ApiResponse<Long>> leaveRoom(@RequestParam Long roomId, Long userId) {
        chatService.leaveRoom(roomId, userId);
        return ResponseEntity.ok(ApiResponse.success(userId));
    }


    //채팅방 채팅내용 불러오기 (방 열기)
    @GetMapping("/chat")
    public ResponseEntity<ApiResponse<List<ChatDto>>> getChatList(Long roomId){
        List<ChatDto> chatList = chatService.getChatList(roomId);
        return ResponseEntity.ok(ApiResponse.success(chatList));
    }


    // 채팅에 참여한 유저 리스트 반환
    @GetMapping("/userlist")
    public ResponseEntity<ApiResponse<List<ChattingUserDto>>> userList(Long roomId) {
        List<ChattingUserDto> userList = chatService.chattingRoomUserList(roomId);
        return ResponseEntity.ok(ApiResponse.success(userList));
    }

}
