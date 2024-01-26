package omg.wecan.chatting.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.chatting.dto.ChatDto;
import omg.wecan.chatting.dto.ChatRoomDto;
import omg.wecan.chatting.dto.ChattingUserDto;
import omg.wecan.chatting.dto.EmoticonDto;
import omg.wecan.chatting.entity.Chatting;
import omg.wecan.chatting.entity.ChattingRoom;
import omg.wecan.chatting.entity.ChattingRoomUser;
import omg.wecan.chatting.repository.ChattingRepository;
import omg.wecan.chatting.repository.ChattingRoomRepository;
import omg.wecan.chatting.repository.ChattingRoomUserRepository;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.shop.entity.Emoticon;
import omg.wecan.shop.entity.UserItem;
import omg.wecan.shop.repository.EmoticonRepository;
import omg.wecan.shop.repository.UserItemRepository;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRoomUserRepository chattingRoomUserRepository;
    private final ChattingRepository chattingRepository;
    private final UserItemRepository userItemRepository;
    private final EmoticonRepository emoticonRepository;


    // roomName 로 채팅방 만들기
    public ChatRoomDto createChatRoom(Long challengeId){
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));
        ChattingRoom chattingRoom = new ChattingRoom().create(challenge);
        chattingRoomRepository.save(chattingRoom);
        return new ChatRoomDto(chattingRoom);
    }

    @Transactional
    public void enterRoom(Long roomId, Long userId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId).orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        chattingRoom.upUserCount();
        ChattingRoomUser chattingRoomUser = new ChattingRoomUser().create(user, chattingRoom);
        chattingRoomUserRepository.save(chattingRoomUser);
    }

    @Transactional
    public void leaveRoom(Long roomId, Long userId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId).orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_NOT_FOUND));
        chattingRoom.downUserCount();

        ChattingRoomUser chattingRoomUser = chattingRoomUserRepository
                .findByChattingRoomIdAndUserUserId(roomId, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_USER_NOT_FOUND));
        chattingRoomUserRepository.delete(chattingRoomUser);
    }

    public List<ChatDto> getChatList(Long roomId) {
        List<Chatting> chats = chattingRepository.findByChattingRoomId(roomId);
        List<ChatDto> chatDTOS = new ArrayList<>();

        for (Chatting chat : chats) {
            ChatDto chatDto = ChatDto.create(chat);
            chatDTOS.add(chatDto);
        }

        return chatDTOS;
    }

    public List<ChattingUserDto> chattingRoomUserList(Long roomId) {
        List<ChattingRoomUser> chattingRoomUsers = chattingRoomUserRepository.findByChattingRoomId(roomId);

        return chattingRoomUsers.stream()
                .map(ChattingUserDto::new)
                .collect(Collectors.toList());
    }

    public ChatDto saveMessage(ChatDto chatDto) {
        Chatting chatting = new Chatting();

        chatting.setChattingRoom(chattingRoomRepository.findById(chatDto.getRoomId())
                .orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_NOT_FOUND, "roomId: " + chatDto.getRoomId())));

        chatting.setChattingRoomUser(chattingRoomUserRepository.findByChattingRoomIdAndUserUserId(chatDto.getRoomId(), chatDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.CHATTING_ROOM_USER_NOT_FOUND, "userId: " + chatDto.getUserId())));

        chatting.setMessage(chatDto.getMessage());
        chatting.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

        chattingRepository.save(chatting);

        chatDto.setTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        ));
        return chatDto;
    }

    public EmoticonDto getEmoticonList(User user) {
        EmoticonDto emoticonDto = new EmoticonDto();
        emoticonDto.setUserId(user.getUserId());

        List<String> emoticonList = new ArrayList<>();
        List<UserItem> userItems = userItemRepository.findByUser(user);

        for (UserItem userItem : userItems) {
            Emoticon emoticon = emoticonRepository.findByItem(userItem.getItem());

            if (emoticon != null) {
                emoticonList.add(emoticon.getImageUrl());
            }
        }
        emoticonDto.setEmoticonList(emoticonList);
        return emoticonDto;

    }
}
