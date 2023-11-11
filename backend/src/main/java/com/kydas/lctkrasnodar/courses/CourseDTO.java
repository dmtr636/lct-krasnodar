package com.kydas.lctkrasnodar.courses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kydas.lctkrasnodar.files.FileDTO;
import com.kydas.lctkrasnodar.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CourseDTO {
    private Long id;

    @NotBlank
    private String name;

    private Long programId;

    @NotNull
    private Integer duration;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID fileId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileDTO file;

    public CourseDTO(Course course) {
        id = course.getId();
        name = course.getName();
        if (course.getFile() != null) {
            file = new FileDTO(course.getFile());
        }
        programId = course.getProgramId();
        duration = course.getDuration();
    }
}