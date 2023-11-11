package com.kydas.lctkrasnodar.programs;


import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/programs")
public class ProgramController {
    private final ProgramService service;

    @GetMapping
    public List<ProgramDTO> all() {
        return service.getAll().stream().map(ProgramDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public ProgramDTO create(@RequestBody @Valid ProgramDTO programDTO) throws ApiException {
        return new ProgramDTO(service.create(programDTO));
    }

    @GetMapping("/{id}")
    public ProgramDTO getById(@PathVariable Long id) throws ApiException {
        return new ProgramDTO(service.getById(id));
    }

    @PutMapping
    public ProgramDTO update(@RequestBody @Valid ProgramDTO programDTO) throws ApiException {
        return new ProgramDTO(service.update(programDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
