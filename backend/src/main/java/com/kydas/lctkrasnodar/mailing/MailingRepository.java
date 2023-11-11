package com.kydas.lctkrasnodar.mailing;

import com.kydas.lctkrasnodar.notification.Notification;
import com.kydas.lctkrasnodar.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailingRepository extends JpaRepository<Mailing, Long> {
}
