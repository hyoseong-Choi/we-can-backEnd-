package omg.wecan.notification.dto;

import lombok.Data;
import omg.wecan.notification.entity.Notifications;

import java.time.format.DateTimeFormatter;

@Data
public class NotificationOutput {
    private Long id;
    private String title;
    private String content;
    private String time;
    private boolean newNotice;
    
    public NotificationOutput(Notifications notifications) {
        this.id = notifications.getId();
        this.title = notifications.getTitle();
        this.content = notifications.getContent();
        this.time = notifications.getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
        this.newNotice = notifications.isNewNotice();
    }
}
