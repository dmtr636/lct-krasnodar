package com.kydas.lctkrasnodar.user;

import com.kydas.lctkrasnodar.core.email.EmailService;
import com.kydas.lctkrasnodar.core.exceptions.AlreadyExistsException;
import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.courses.UserCourseService;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileRepository;
import com.kydas.lctkrasnodar.notification.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileRepository fileRepository;
    private final UserCourseService userCourseService;
    private final EmailService emailService;
    private final NotificationService notificationService;

    @Value("${spring.security.user.name}")
    private String adminUserEmail;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAllByEmailIsNot(adminUserEmail);
    }

    @Transactional
    public User getUserById(Long id) throws ApiException {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public User createUser(UserDTO userDTO) throws ApiException {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new AlreadyExistsException();
        }
        String password = userDTO.getPassword();
        if (password == null) {
            password = alphaNumericString(8);
            emailService.sendEmail(
                userDTO.getEmail(),
                "Пароль от аккаунта на hack-kydas.ru",
                "Ваш пароль: " + password
            );
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        setUserFields(user, userDTO);
        user.setPassword(encodedPassword);
        user = userRepository.save(user);

        if (user.getRole() == User.Role.EMPLOYEE) {
            userCourseService.assignCoursesToUser(user);
            notificationService.notifyAddSkillsInProfile(user);
        }

        return user;
    }

    public User updateUser(UserDTO userDTO) throws ApiException {
        Long userId = userDTO.getId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        if (userRepository.existsByEmailAndIdIsNot(userDTO.getEmail(), userId)) {
            throw new AlreadyExistsException();
        }

        setUserFields(user, userDTO);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String alphaNumericString(int len) {
        String AB = "23456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    private void setUserFields(User user, UserDTO userDTO) throws RelatedObjectNotFoundException {
        User.Role role = getRole(userDTO);
        File photoFile = getPhotoFile(userDTO);
        User responsibleUser = getResponsibleUser(userDTO);

        user
            .setRole(role)
            .setPhotoFile(photoFile)
            .setEmail(userDTO.getEmail())
            .setLastName(userDTO.getLastName())
            .setFirstName(userDTO.getFirstName())
            .setPatronymic(userDTO.getPatronymic())
            .setDepartment(userDTO.getDepartment())
            .setPhone(userDTO.getPhone())
            .setTelegram(userDTO.getTelegram())
            .setResponsibleUser(responsibleUser)
            .setSkills(userDTO.getSkills());
    }

    private User.Role getRole(UserDTO userDTO) {
        User.Role role = userDTO.getRole();
        if (role != null) {
            return role;
        }
        User.Department department = userDTO.getDepartment();
        if (department == User.Department.HR || department == User.Department.MANAGER) {
            return User.Role.MANAGER;
        }
        return User.Role.EMPLOYEE;
    }

    private File getPhotoFile(UserDTO userDTO) throws RelatedObjectNotFoundException {
        UUID fileId = userDTO.getPhotoFileId();
        if (fileId == null) {
            return null;
        }
        return fileRepository
            .findById(userDTO.getPhotoFileId())
            .orElseThrow(() -> new RelatedObjectNotFoundException("photoFileId"));
    }

    private User getResponsibleUser(UserDTO userDTO) throws RelatedObjectNotFoundException {
        Long responsibleUserId = userDTO.getResponsibleUserId();
        if (responsibleUserId == null) {
            return null;
        }
        return userRepository
            .findById(responsibleUserId)
            .orElseThrow(() -> new RelatedObjectNotFoundException("responsibleUserId"));
    }
}
