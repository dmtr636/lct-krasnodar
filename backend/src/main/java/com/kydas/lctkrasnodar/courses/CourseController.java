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
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    @GetMapping
    public List<CourseDTO> all() {
        List<Course> courses = service.getAll();
        return courses.stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public CourseDTO create(@RequestBody @Valid CourseDTO courseDTO) throws ApiException {
        return new CourseDTO(service.create(courseDTO));
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Long id) throws ApiException {
        return new CourseDTO(service.getById(id));
    }

    @PutMapping
    public CourseDTO update(@RequestBody @Valid CourseDTO courseDTO) throws ApiException {
        return new CourseDTO(service.update(courseDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
