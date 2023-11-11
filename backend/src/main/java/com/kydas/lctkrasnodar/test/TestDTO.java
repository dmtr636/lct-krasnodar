package com.kydas.lctkrasnodar.test;

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
public class TestDTO {
    private Long id;

    @NotBlank
    private String question;

    @NotNull
    private List<String> answers;

    @NotNull
    private Integer correctAnswerIndex;

    private Long courseId;

    public TestDTO(Test test) {
        id = test.getId();
        question = test.getQuestion();
        answers = test.getAnswers();
        correctAnswerIndex = test.getCorrectAnswerIndex();
        courseId = test.getCourseId();
    }
}