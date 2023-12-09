package omg.wecan.notification.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
}
