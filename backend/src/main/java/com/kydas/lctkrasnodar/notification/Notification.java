package com.kydas.lctkrasnodar.notification;

import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String text;

    private Type type;

    private String url;

    private Boolean isRead = false;

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public enum Type {
        COURSE_ASSIGN, COURSE_DEADLINE, EMPLOYEE_COURSE_DEADLINE, ADD_SKILLS_IN_PROFILE
    }
}
