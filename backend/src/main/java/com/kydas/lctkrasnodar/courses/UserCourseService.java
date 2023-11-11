package com.kydas.lctkrasnodar.courses;

import com.kydas.lctkrasnodar.audit.AuditEvent;
import com.kydas.lctkrasnodar.audit.AuditService;
import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.notification.NotificationService;
import com.kydas.lctkrasnodar.programs.Program;
import com.kydas.lctkrasnodar.programs.ProgramService;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ProgramService programService;
    private final CourseService courseService;
    private final NotificationService notificationService;
    private final AuditService auditService;

    @Transactional
    public List<UserCourse> getAll() {
        return userCourseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public UserCourse getById(Long id) throws ApiException {
        return userCourseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public UserCourse create(UserCourseDTO userCourseDTO) throws ApiException {
        UserCourse userCourse = new UserCourse();
        setFields(userCourse, userCourseDTO);
        notificationService.notifyCoursesAssign(getUser(userCourseDTO));
        return userCourseRepository.save(userCourse);
    }

    public UserCourse update(UserCourseDTO userCourseDTO) throws ApiException {
        Long id = userCourseDTO.getId();
        UserCourse userCourse = userCourseRepository.findById(id).orElseThrow(NotFoundException::new);
        if (userCourse.getFinishTimestamp() == null && userCourseDTO.getFinishTimestamp() != null) {
            Course course = courseRepository.findById(userCourseDTO.getCourseId()).orElseThrow();
            auditService.addEvent(AuditEvent.EventType.COURSE_FINISHED, "Завершил курс: " + course.getName());
        }
        setFields(userCourse, userCourseDTO);
        return userCourseRepository.save(userCourse);
    }

    public void delete(Long id) {
        userCourseRepository.deleteById(id);
    }

    public void assignCoursesToUser(User user) throws ApiException {
        boolean isFirstCourse = true;
        List<Program> programs = programService.getProgramsByDepartment(user.getDepartment());
        for (Program program : programs) {
            List<Course> courses = courseService.getAllByProgram(program);
            for (Course course : courses) {
                UserCourse userCourse = new UserCourse();
                userCourse.setCourse(course);
                userCourse.setUser(user);
                if (isFirstCourse) {
                    userCourse.setStartTimestamp(Instant.now());
                    isFirstCourse = false;
                }
                userCourseRepository.save(userCourse);
                notificationService.notifyCoursesAssign(user);
            }
        }
    }

    private void setFields(UserCourse userCourse, UserCourseDTO userCourseDTO) throws RelatedObjectNotFoundException {
        User user = getUser(userCourseDTO);
        Course course = getCourse(userCourseDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        Instant startTimestamp = userCourseDTO.getStartTimestamp() != null
            ? Instant.from(formatter.parse(userCourseDTO.getStartTimestamp()))
            : null;
        Instant finishTimestamp = userCourseDTO.getFinishTimestamp() != null
            ? Instant.from(formatter.parse(userCourseDTO.getFinishTimestamp()))
            : null;

        userCourse
            .setUser(user)
            .setCourse(course)
            .setStartTimestamp(startTimestamp)
            .setFinishTimestamp(finishTimestamp)
            .setRating(userCourseDTO.getRating())
            .setTestScore(userCourseDTO.getTestScore());
    }

    private User getUser(UserCourseDTO userCourseDTO) throws RelatedObjectNotFoundException {
        Long userId = userCourseDTO.getUserId();
        if (userId == null) {
            return null;
        }
        return userRepository
            .findById(userId)
            .orElseThrow(() -> new RelatedObjectNotFoundException("userId"));
    }

    private Course getCourse(UserCourseDTO userCourseDTO) throws RelatedObjectNotFoundException {
        Long id = userCourseDTO.getCourseId();
        if (id == null) {
            return null;
        }
        return courseRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("courseId"));
    }
}
