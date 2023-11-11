package com.kydas.lctkrasnodar.test;

import com.kydas.lctkrasnodar.courses.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @ElementCollection
    private List<String> answers;

    @Column(nullable = false)
    private Integer correctAnswerIndex;

    @ManyToOne
    private Course course;

    public Long getCourseId() {
        return course != null ? course.getId() : null;
    }
}
