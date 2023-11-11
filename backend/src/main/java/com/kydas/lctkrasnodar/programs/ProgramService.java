package com.kydas.lctkrasnodar.programs;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    @Transactional
    public List<Program> getAll() {
        return programRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Program getById(Long id) throws ApiException {
        return programRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Program create(ProgramDTO programDTO) {
        Program program = new Program();
        setFields(program, programDTO);
        return programRepository.save(program);
    }

    public Program update(ProgramDTO programDTO) throws ApiException {
        Long id = programDTO.getId();
        Program program = programRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(program, programDTO);
        return programRepository.save(program);
    }

    public void deleteById(Long id) {
        programRepository.deleteById(id);
    }

    public List<Program> getProgramsByDepartment(User.Department department) {
        return programRepository.findAllByDepartmentsContainsOrDepartmentsEmptyOrderByIdDesc(department);
    }

    private void setFields(Program program, ProgramDTO programDTO) {
        program
            .setName(programDTO.getName())
            .setDepartments(programDTO.getDepartments());
    }
}
