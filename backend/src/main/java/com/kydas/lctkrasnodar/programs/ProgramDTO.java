package com.kydas.lctkrasnodar.programs;

import com.kydas.lctkrasnodar.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ProgramDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private List<User.Department> departments;

    public ProgramDTO(Program program) {
        id = program.getId();
        name = program.getName();
        departments = program.getDepartments();
    }
}