package com.kydas.lctkrasnodar.programs;

import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    private List<User.Department> departments;

    @OneToMany(mappedBy = "program")
    private Set<Course> courses;
}
