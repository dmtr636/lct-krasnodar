package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.test.Test;
import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    private Instant startTimestamp;

    private Instant finishTimestamp;

    private Integer rating;

    private Integer testScore;

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Long getCourseId() {
        return course != null ? course.getId() : null;
    }

    public String getStartTimestampString() {
        return startTimestamp != null
            ? startTimestamp.toString()
            : null;
    }

    public String getFinishTimestampString() {
        return finishTimestamp != null
            ? finishTimestamp.toString()
            : null;
    }
}
