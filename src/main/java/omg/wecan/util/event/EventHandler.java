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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandler {
    private final NotificationRepository notificationRepository;
    
    @EventListener
    @Async
    public void handleRecruitCommentEvent(RecruitCommentEvent recruitCommentEvent) {
        Notifications notifications = new Notifications(recruitCommentEvent);
//
//        Message message = Message.builder()
//                .setToken(notifications.getTargetUser().getFcmToken())
//                .setNotification(Notification.builder()
//                        .setTitle(notifications.getTitle())
//                        .setBody(notifications.getContent())
//                        .build())
//                .build();
//
//        try {
//            String response = FirebaseMessaging.getInstance().send(message);
//            log.info("Successfully sent message: {}", response);
//            notificationRepository.save(new Notifications(recruitCommentEvent));
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
    
    @EventListener
    @Async
    public void handleMinimumParticipateEvent(MinimumParticipateEvent minimumParticipateEvent) {
        List<Notifications> notifications = new LinkedList<>();
//        for (Participate participate : minimumParticipateEvent.getParticipateList()) {
//            if (participate.isLeader()) {
//                notifications.add(new Notifications(participate, "모집한"));
//                continue;
//            }
//            notifications.add(new Notifications(participate, "신청한"));
//        }
//
//        try {
//            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEach(notifications.stream()
//                    .map(notification -> Message.builder().setToken(notification.getTargetUser().getFcmToken())
//                            .setNotification(Notification.builder()
//                                    .setTitle(notification.getTitle())
//                                    .setBody(notification.getContent())
//                                    .build())
//                            .build()).toList());
//            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
//            notificationRepository.saveAll(notifications);
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
    
    @EventListener
    @Async
    public void handleChallengeStartEvent(ChallengeStartEvent challengeStartEvent) {
        List<Notifications> notifications = new LinkedList<>();
//        for (Participate participate : challengeStartEvent.getParticipateList()) {
//            notifications.add(new Notifications(challengeStartEvent.getRecruitTitle(), participate));
//        }
//
//        try {
//            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEach(notifications.stream()
//                    .map(notification -> Message.builder().setToken(notification.getTargetUser().getFcmToken())
//                            .setNotification(Notification.builder()
//                                    .setTitle(notification.getTitle())
//                                    .setBody(notification.getContent())
//                                    .build())
//                            .build()).toList());
//            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
//            notificationRepository.saveAll(notifications);
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleBuyItemEvent(BuyItemEvent buyItemEvent) {
        Notifications notification = new Notifications(buyItemEvent);
//
//        try {
//            String response = FirebaseMessaging.getInstance().send(Message.builder()
//                    .setToken(buyItemEvent.getUser().getFcmToken())
//                    .setNotification(Notification.builder()
//                            .setTitle(notification.getTitle())
//                            .setBody(notification.getContent())
//                            .build())
//                    .build());
//            log.info("Successfully sent message: {}", response);
//            notificationRepository.save(notification);
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to user push message. error info : {}", e.getMessage());
//        }
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePayChallengeEvent(PayChallengeEvent payChallengeEvent) {
        notificationRepository.save(new Notifications(payChallengeEvent));
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleSettleChallengeEvent(SettleChallengeEvent settleChallengeEvent) {
        notificationRepository.save(new Notifications(settleChallengeEvent));
    }
}
