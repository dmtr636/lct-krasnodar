package com.kydas.lctkrasnodar.audit;

import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
public class AuditEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant timestamp;

    @Column(nullable = false)
    private EventType eventType;

    private String text;

    @ManyToOne(optional = false)
    private User user;

    public enum EventType {LOGIN, LOGOUT, COURSE_FINISHED}
}
