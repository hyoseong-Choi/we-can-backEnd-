package omg.wecan.util.event;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.notification.entity.Notifications;
import omg.wecan.notification.repository.NotificationRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandler {
    private final NotificationRepository notificationRepository;
    @EventListener
    @Async
    public void handleRecruitCommentEvent(RecruitCommentEvent recruitCommentEvent) {
        //로그인하면 레디스에 유저아이디, 토큰 저장
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
}
