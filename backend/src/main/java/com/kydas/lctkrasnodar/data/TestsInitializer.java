package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileService;
import com.kydas.lctkrasnodar.test.Test;
import com.kydas.lctkrasnodar.test.TestDTO;
import com.kydas.lctkrasnodar.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class TestsInitializer {
    private final TestService testService;

    public List<Test> createTests(List<Course> courses) throws IOException, ApiException {
        List<Test> tests = new ArrayList<>();

        for (Course course: courses) {
            TestDTO test1 = new TestDTO()
                .setQuestion("Кто написал поэму «Руслан и Людмила»")
                .setCourseId(course.getId())
                .setAnswers(List.of("Пушкин А.С.", "Толстой Л.Н.", "Лермонтов М.Ю."))
                .setCorrectAnswerIndex(0);

            TestDTO test2 = new TestDTO()
                .setQuestion("Сколько грамм в килограмме")
                .setCourseId(course.getId())
                .setAnswers(List.of("100", "1000", "10000"))
                .setCorrectAnswerIndex(1);

            tests.add(testService.create(test1));
            tests.add(testService.create(test2));
        }
        return tests;
    }
}
