package com.kydas.lctkrasnodar.notification;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.core.security.SecurityContext;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Notification> getAll() throws ApiException {
        return notificationRepository.findAll();
    }

    @Transactional
    public Notification getById(Long id) throws ApiException {
        return notificationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Notification create(NotificationDTO courseDTO) throws ApiException {
        Notification notification = new Notification();
        setFields(notification, courseDTO);

        return notificationRepository.save(notification);
    }

    public Notification update(NotificationDTO notificationDTO) throws ApiException {
        Long id = notificationDTO.getId();
        Notification course = notificationRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(course, notificationDTO);
        return notificationRepository.save(course);
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    public void notifyCoursesAssign(User user) throws ApiException {
        if (!notificationRepository.existsByUserAndTypeAndIsRead(user, Notification.Type.COURSE_ASSIGN, false)) {
            Notification notification = new Notification()
                .setUser(user)
                .setText("Вам назначены курсы")
                .setType(Notification.Type.COURSE_ASSIGN)
                .setUrl("/education");
            notificationRepository.save(notification);
        }
    }

    public void notifyAddSkillsInProfile(User user) throws ApiException {
        if (!notificationRepository.existsByUserAndTypeAndIsRead(user, Notification.Type.ADD_SKILLS_IN_PROFILE, false)) {
            Notification notification = new Notification()
                .setUser(user)
                .setText("Заполните свои навыки в профиле")
                .setType(Notification.Type.ADD_SKILLS_IN_PROFILE)
                .setUrl("/users/" + user.getId());
            notificationRepository.save(notification);
        }
    }

    private void setFields(Notification notification, NotificationDTO notificationDTO) throws RelatedObjectNotFoundException {
        User user = getUser(notificationDTO);

        notification
            .setUser(user)
            .setText(notificationDTO.getText())
            .setType(notificationDTO.getType())
            .setIsRead(notificationDTO.getIsRead())
            .setUrl(notificationDTO.getUrl());
    }

    private User getUser(NotificationDTO notificationDTO) throws RelatedObjectNotFoundException {
        Long id = notificationDTO.getUserId();
        if (id == null) {
            return null;
        }
        return userRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("userId"));
    }
}
