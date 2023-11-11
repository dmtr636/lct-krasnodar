package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userCourses")
public class UserCourseController {
    private final UserCourseService service;

    @GetMapping
    public List<UserCourseDTO> all() {
        List<UserCourse> userUserCourses = service.getAll();
        return userUserCourses.stream().map(UserCourseDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public UserCourseDTO create(@RequestBody @Valid UserCourseDTO userUserCourseDTO) throws ApiException {
        return new UserCourseDTO(service.create(userUserCourseDTO));
    }

    @GetMapping("/{id}")
    public UserCourseDTO getById(@PathVariable Long id) throws ApiException {
        return new UserCourseDTO(service.getById(id));
    }

    @PutMapping
    public UserCourseDTO update(@RequestBody @Valid UserCourseDTO userUserCourseDTO) throws ApiException {
        return new UserCourseDTO(service.update(userUserCourseDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
