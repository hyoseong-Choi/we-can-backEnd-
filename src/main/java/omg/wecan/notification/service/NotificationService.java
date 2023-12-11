package omg.wecan.notification.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.notification.dto.NotificationOutput;
import omg.wecan.notification.repository.NotificationRepository;
import omg.wecan.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    
    public List<NotificationOutput> findNotification(User loginUser) {
        return notificationRepository.findByTargetUserOrderByIdDesc(loginUser).stream().map(NotificationOutput::new).collect(Collectors.toList());
    }
    
    public Long deleteNotification(Long id) {
        notificationRepository.deleteById(id);
        return id;
    }
}
