package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileService;
import com.kydas.lctkrasnodar.mailing.Mailing;
import com.kydas.lctkrasnodar.programs.Program;
import com.kydas.lctkrasnodar.test.Test;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final ImageInitializer imageInitializer;
    private final UserInitializer userInitializer;
    private final ProgramInitializer programInitializer;
    private final CourseInitializer courseInitializer;
    private final MailingInitializer mailingInitializer;
    private final DocumentInitializer documentInitializer;
    private final TestsInitializer testsInitializer;

    public void run(ApplicationArguments args) throws IOException, ApiException {
        if (!userInitializer.isAdminUserExists()) {
            initializeData();
        }
    }

    private void initializeData() throws IOException, ApiException {
        List<File> images = imageInitializer.createImages();
        List<Program> programs = programInitializer.createPrograms();
        List<File> documents = documentInitializer.createDocuments();
        List<Course> courses = courseInitializer.createCourses(programs, documents);
        List<Test> tests = testsInitializer.createTests(courses);
        List<Mailing> mailings = mailingInitializer.createMailings();
        List<User> users = userInitializer.createUsers(images);
    }
}