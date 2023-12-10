package omg.wecan.notification.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.notification.dto.NotificationOutput;
import omg.wecan.notification.service.NotificationService;
import omg.wecan.user.entity.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    
    @GetMapping("/notification")
    public List<NotificationOutput> notificationList(@AuthenticationPrincipal User loginUser) {
        return notificationService.findNotification(loginUser);
    }
    
    @DeleteMapping("/notification/{id}")
    public Long notificationDelete(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return notificationService.deleteNotification(id);
    }
}
