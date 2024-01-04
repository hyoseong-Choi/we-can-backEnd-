package omg.wecan.notification.repository;

import omg.wecan.notification.entity.Notifications;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findByTargetUserOrderByIdDesc(User targetUser);
}
