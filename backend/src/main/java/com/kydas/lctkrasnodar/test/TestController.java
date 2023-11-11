package com.kydas.lctkrasnodar.test;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService service;

    @GetMapping
    public List<TestDTO> all() {
        return service.getAll().stream().map(TestDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public TestDTO create(@RequestBody @Valid TestDTO testDTO) throws ApiException {
        return new TestDTO(service.create(testDTO));
    }

    @GetMapping("/{id}")
    public TestDTO getById(@PathVariable Long id) throws ApiException {
        return new TestDTO(service.getById(id));
    }

    @PutMapping
    public TestDTO update(@RequestBody @Valid TestDTO testDTO) throws ApiException {
        return new TestDTO(service.update(testDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
