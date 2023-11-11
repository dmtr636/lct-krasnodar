package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.programs.Program;
import com.kydas.lctkrasnodar.programs.ProgramDTO;
import com.kydas.lctkrasnodar.programs.ProgramService;
import com.kydas.lctkrasnodar.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProgramInitializer {
    private final ProgramService programService;

    public List<Program> createPrograms() throws IOException {
        List<ProgramDTO> programs = new ArrayList<>();

        programs.add(new ProgramDTO()
            .setName("Client Service")
            .setDepartments(List.of(User.Department.CLIENT_SERVICE))
        );

        programs.add(new ProgramDTO()
            .setName("Design")
            .setDepartments(List.of(User.Department.DESIGN))
        );

        programs.add(new ProgramDTO()
            .setName("Engineering")
            .setDepartments(List.of(User.Department.DEVELOPMENT))
        );

        programs.add(new ProgramDTO()
            .setName("Project Management")
            .setDepartments(List.of(User.Department.PROJECT_MANAGEMENT))
        );

        programs.add(new ProgramDTO()
            .setName("Добро пожаловать в компанию")
            .setDepartments(new ArrayList<>())
        );

        programs.add(new ProgramDTO()
            .setName("Первые шаги")
            .setDepartments(new ArrayList<>())
        );

        List<Program> createdPrograms = new ArrayList<>();
        for (ProgramDTO programDTO : programs) {
            createdPrograms.add(programService.create(programDTO));
        }
        return createdPrograms;
    }
}
