package omg.wecan.notification.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.notification.dto.NotificationOutput;
import omg.wecan.notification.entity.Notifications;
import omg.wecan.notification.repository.NotificationRepository;
import omg.wecan.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Transactional
    public List<NotificationOutput> findNotification(User loginUser) {
        List<Notifications> notifications = notificationRepository.findByTargetUserOrderByIdDesc(loginUser);
        List<NotificationOutput> notificationOutputs = notifications.stream().map(NotificationOutput::new).collect(Collectors.toList());
        for (Notifications notification : notifications) {
            notification.readNotifications();
        }
        return notificationOutputs;
    }
    @Transactional
    public Long deleteNotification(Long id) {
        notificationRepository.deleteById(id);
        return id;
    }
}
