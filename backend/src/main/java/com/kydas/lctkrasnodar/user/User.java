package com.kydas.lctkrasnodar.user;

import com.kydas.lctkrasnodar.audit.AuditEvent;
import com.kydas.lctkrasnodar.courses.UserCourse;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.messages.Message;
import com.kydas.lctkrasnodar.notification.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private String telegram;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    private File photoFile;

    @CreationTimestamp
    private Instant createTimestamp;

    @ManyToOne
    private User responsibleUser;

    @OneToMany(mappedBy = "responsibleUser", cascade = CascadeType.REMOVE)
    private Set<User> responsibleForUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<UserCourse> courses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<AuditEvent> events;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Message> messages;

    @ElementCollection
    private List<String> skills;

    public String getPhotoFileUrl() {
        return photoFile != null ? photoFile.getUrl() : null;
    }

    public String getFullName() {
        return String.join(" ", List.of(lastName, firstName, patronymic));
    }

    public Long getResponsibleUserId() {
        return responsibleUser != null ? responsibleUser.getId() : null;
    }

    public enum Department {DESIGN, DEVELOPMENT, PROJECT_MANAGEMENT, CLIENT_SERVICE, HR, MANAGER, ADMIN}

    public enum Role {ADMIN, MANAGER, EMPLOYEE}
}
