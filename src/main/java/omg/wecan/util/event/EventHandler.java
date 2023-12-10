package omg.wecan.util.event;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.notification.entity.Notifications;
import omg.wecan.notification.repository.NotificationRepository;
import omg.wecan.recruit.entity.Participate;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandler {
    private final NotificationRepository notificationRepository;
    
    @EventListener
    @Async
    public void handleRecruitCommentEvent(RecruitCommentEvent recruitCommentEvent) {
        //로그인하면 레디스에 유저아이디, 토큰 저장?
        log.info("이벤트 Thread Id : {}", Thread.currentThread().getId());
        Notifications notifications = notificationRepository.save(new Notifications(recruitCommentEvent));
//        Notification notification = Notification.builder()
//                .setTitle("누군가 " + recruitCommentEvent.getUser().getNickName() + " 님을 멘션했어요.")
//                .setBody(recruitCommentEvent.getContent())
//                .build();
//
//        Message message = Message.builder()
//                .setToken(recruitCommentEvent.getUser().getNickName())
//                .setNotification(notification)
//                .build();
//
//        try {
//            String response = FirebaseMessaging.getInstance().send(message);
//            log.info("Successfully sent message: {}", response);
//            Notifications notifications = notificationRepository.save(new Notifications(recruitCommentEvent));
//            log.info("이벤트 Thread Id : {}, 알림 : {}", Thread.currentThread().getId(), notifications);
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
    
    @EventListener
    @Async
    public void handleMinimumParticipateEvent(MinimumParticipateEvent minimumParticipateEvent) {
        log.info("이벤트 Thread Id : {}", Thread.currentThread().getId());
        for (Participate participate : minimumParticipateEvent.getParticipateList()) {
            notificationRepository.save(new Notifications(participate, minimumParticipateEvent.getRecruitTitle()));
        }
//
//        Notification notification = Notification.builder()
//                .setTitle("챌린지 최소 모집 인원이 다 모였어요.")
//                .setBody("모집이 끝나면 챌린지를 시작할 수 있어요.")
//                .build();
//
//        MulticastMessage multicastMessage = MulticastMessage.builder()
//                .addAllTokens(minimumParticipateEvent.getParticipateList().stream()
//                        .map(participate -> participate.getUser().getNickName()).collect(Collectors.toList()))
//                .setNotification(notification)
//                .build();
//
//        try {
//            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEachForMulticast(multicastMessage);
//            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
//            for (Participate participate : minimumParticipateEvent.getParticipateList()) {
//                Notifications notifications = notificationRepository.save(new Notifications(participate, minimumParticipateEvent.getRecruitTitle()));
//                log.info("이벤트 Thread Id : {}, 알림 : {}", Thread.currentThread().getId(), notifications);
//            }
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
}
