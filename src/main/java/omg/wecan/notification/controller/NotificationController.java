package omg.wecan.notification.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.notification.service.NotificationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    
}
