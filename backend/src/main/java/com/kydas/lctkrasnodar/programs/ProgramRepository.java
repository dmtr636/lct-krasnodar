package com.kydas.lctkrasnodar.programs;

import com.kydas.lctkrasnodar.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAllByDepartmentsContainsOrDepartmentsEmptyOrderByIdDesc(User.Department departments);
}
