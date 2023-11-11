package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.programs.Program;
import com.kydas.lctkrasnodar.test.Test;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private File file;

    @ManyToOne
    private Program program;

    @Column(nullable = false)
    private Integer duration;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<UserCourse> userCourses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Set<Test> tests;

    public Long getProgramId() {
        return program != null ? program.getId() : null;
    }

    public String getFileUrl() {
        return file != null ? file.getUrl() : null;
    }

    public String getFileName() {
        return file != null ? file.getFileName() : null;
    }
}
