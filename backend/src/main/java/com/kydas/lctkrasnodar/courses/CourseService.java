package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileRepository;
import com.kydas.lctkrasnodar.programs.Program;
import com.kydas.lctkrasnodar.programs.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final FileRepository fileRepository;
    private final ProgramRepository programRepository;

    @Transactional
    public List<Course> getAll() {
        return courseRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Course getById(Long id) throws ApiException {
        return courseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Course create(CourseDTO courseDTO) throws ApiException {
        Course course = new Course();
        setFields(course, courseDTO);

        course = courseRepository.save(course);
        return course;
    }

    public Course update(CourseDTO courseDTO) throws ApiException {
        Long id = courseDTO.getId();
        Course course = courseRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(course, courseDTO);
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public List<Course> getAllByProgram(Program program) {
        return courseRepository.findAllByProgram(program);
    }

    private void setFields(Course course, CourseDTO courseDTO) throws RelatedObjectNotFoundException {
        File file = getFile(courseDTO);
        Program program = getProgram(courseDTO);

        course
            .setName(courseDTO.getName())
            .setDuration(courseDTO.getDuration())
            .setFile(file)
            .setProgram(program);
    }

    private File getFile(CourseDTO courseDTO) throws RelatedObjectNotFoundException {
        UUID fileId = courseDTO.getFileId();
        if (fileId == null) {
            return null;
        }
        return fileRepository
            .findById(fileId)
            .orElseThrow(() -> new RelatedObjectNotFoundException("fileId"));
    }

    private Program getProgram(CourseDTO courseDTO) throws RelatedObjectNotFoundException {
        Long id = courseDTO.getProgramId();
        if (id == null) {
            return null;
        }
        return programRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("programId"));
    }
}
