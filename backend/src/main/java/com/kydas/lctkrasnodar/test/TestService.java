package com.kydas.lctkrasnodar.test;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.courses.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public List<Test> getAll() {
        return testRepository.findAll();
    }

    @Transactional
    public Test getById(Long id) throws ApiException {
        return testRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Test create(TestDTO testDTO) throws ApiException {
        Test test = new Test();
        setFields(test, testDTO);
        return testRepository.save(test);
    }

    public Test update(TestDTO testDTO) throws ApiException {
        Long id = testDTO.getId();
        Test test = testRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(test, testDTO);
        return testRepository.save(test);
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    private void setFields(Test test, TestDTO testDTO) throws RelatedObjectNotFoundException {
        Course course = getCourse(testDTO);

        test
            .setQuestion(testDTO.getQuestion())
            .setAnswers(testDTO.getAnswers())
            .setCorrectAnswerIndex(testDTO.getCorrectAnswerIndex())
            .setCourse(course);
    }

    private Course getCourse(TestDTO testDTO) throws RelatedObjectNotFoundException {
        Long id = testDTO.getCourseId();
        if (id == null) {
            return null;
        }
        return courseRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("courseId"));
    }
}
