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
//        notificationRepository.save(notifications);
        Message message = Message.builder()
                .setToken(notifications.getTargetUser().getFcmToken())
                .setNotification(Notification.builder()
                        .setTitle(notifications.getTitle())
                        .setBody(notifications.getContent())
                        .build())
                .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                        .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                        .build()).build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Successfully sent message: {}", response);
            notificationRepository.save(new Notifications(recruitCommentEvent));
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
    
    @EventListener
    @Async
    public void handleMinimumParticipateEvent(MinimumParticipateEvent minimumParticipateEvent) {
        List<Notifications> notifications = new LinkedList<>();
        for (Participate participate : minimumParticipateEvent.getParticipateList()) {
            if (participate.isLeader()) {
                notifications.add(new Notifications(participate, "모집한"));
                continue;
            }
            notifications.add(new Notifications(participate, "신청한"));
        }
//        notificationRepository.saveAll(notifications);
        try {
            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEach(notifications.stream()
                    .map(notification -> Message.builder().setToken(notification.getTargetUser().getFcmToken())
                            .setNotification(Notification.builder()
                                    .setTitle(notification.getTitle())
                                    .setBody(notification.getContent())
                                    .build())
                            .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                                    .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                                    .build()).build())
                            .build()).toList());
            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
            notificationRepository.saveAll(notifications);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }

    @EventListener
    @Async
    public void handleParticipateFailEvent(ParticipateFailEvent participateFailEvent) {
        List<Notifications> notifications = new LinkedList<>();
        for (Participate participate : participateFailEvent.getParticipateList()) {
            notifications.add(new Notifications(participate, participateFailEvent.getRecruitTitle(), "fail"));
        }
        
        try {
            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEach(notifications.stream()
                    .map(notification -> Message.builder().setToken(notification.getTargetUser().getFcmToken())
                            .setNotification(Notification.builder()
                                    .setTitle(notification.getTitle())
                                    .setBody(notification.getContent())
                                    .build())
                            .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                                    .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                                    .build()).build())
                            .build()).toList());
            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
            notificationRepository.saveAll(notifications);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
    
    @EventListener
    @Async
    public void handleChallengeStartEvent(ChallengeStartEvent challengeStartEvent) {
        List<Notifications> notifications = new LinkedList<>();
        for (Participate participate : challengeStartEvent.getParticipateList()) {
            notifications.add(new Notifications(challengeStartEvent.getRecruitTitle(), participate));
        }
//        notificationRepository.saveAll(notifications);
        try {
            BatchResponse batchResponse = FirebaseMessaging.getInstance().sendEach(notifications.stream()
                    .map(notification -> Message.builder().setToken(notification.getTargetUser().getFcmToken())
                            .setNotification(Notification.builder()
                                    .setTitle(notification.getTitle())
                                    .setBody(notification.getContent())
                                    .build())
                            .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                                    .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                                    .build()).build())
                            .build()).toList());
            log.info(batchResponse.getSuccessCount() + " Successfully sent message");
            notificationRepository.saveAll(notifications);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleBuyItemEvent(BuyItemEvent buyItemEvent) {
        Notifications notification = new Notifications(buyItemEvent);
//        notificationRepository.save(notification);
        try {
            String response = FirebaseMessaging.getInstance().send(Message.builder()
                    .setToken(notification.getTargetUser().getFcmToken())
                    .setNotification(Notification.builder()
                            .setTitle(notification.getTitle())
                            .setBody(notification.getContent())
                            .build())
                    .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                            .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                            .build()).build())
                    .build());
            log.info("Successfully sent message: {}", response);
            notificationRepository.save(notification);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePayChallengeEvent(PayChallengeEvent payChallengeEvent) {
        Notifications notification = new Notifications(payChallengeEvent);
        
        try {
            String response = FirebaseMessaging.getInstance().send(Message.builder()
                    .setToken(notification.getTargetUser().getFcmToken())
                    .setNotification(Notification.builder()
                            .setTitle(notification.getTitle())
                            .setBody(notification.getContent())
                            .build())
                    .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                            .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                            .build()).build())
                    .build());
            log.info("Successfully sent message: {}", response);
            notificationRepository.save(notification);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
    
    @TransactionalEventListener
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleSettleChallengeEvent(SettleChallengeEvent settleChallengeEvent) {
        Notifications notification = new Notifications(settleChallengeEvent);
        
        try {
            String response = FirebaseMessaging.getInstance().send(Message.builder()
                    .setToken(notification.getTargetUser().getFcmToken())
                    .setNotification(Notification.builder()
                            .setTitle(notification.getTitle())
                            .setBody(notification.getContent())
                            .build())
                    .setWebpushConfig(WebpushConfig.builder().setNotification(WebpushNotification.builder()
                            .setBadge("https://cdn.discordapp.com/attachments/1198333678305157143/1198561733879549992/dc824826bc0482c9.png?ex=65bf5a99&is=65ace599&hm=728ce0a712db36a502ca420d62cea18a0e86bb2bd7f9430d6fa9b9e4deaeab2c&")
                            .build()).build())
                    .build());
            log.info("Successfully sent message: {}", response);
            notificationRepository.save(notification);
        } catch (FirebaseMessagingException e) {
            log.error("cannot send to user push message. error info : {}", e.getMessage());
        }
    }
}
