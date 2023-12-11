package omg.wecan.notification.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.notification.dto.NotificationOutput;
import omg.wecan.notification.service.NotificationService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<NotificationOutput>>> notificationList(@AuthenticationPrincipal User loginUser) {
        return ResponseEntity.ok(ApiResponse.success(notificationService.findNotification(loginUser)));
    }
    
    @DeleteMapping("/notification/{id}")
    public ResponseEntity<ApiResponse<Long>> notificationDelete(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(notificationService.deleteNotification(id)));
    }
}
