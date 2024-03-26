package omg.wecan.notification.dto;

import lombok.Data;
import omg.wecan.notification.entity.Notifications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NotificationOutput {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime time;
    private boolean newNotice;
    
    public NotificationOutput(Notifications notifications) {
        this.id = notifications.getId();
        this.title = notifications.getTitle();
        this.content = notifications.getContent();
        this.time = notifications.getCreatedAt();
        this.newNotice = notifications.isNewNotice();
    }
}
