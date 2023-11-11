package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.programs.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByProgram(Program program);
}
