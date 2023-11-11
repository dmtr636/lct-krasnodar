package com.kydas.lctkrasnodar.notification;

import com.kydas.lctkrasnodar.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUser(User user);
    Boolean existsByUserAndTypeAndIsRead(User user, Notification.Type type, Boolean isRead);
}
