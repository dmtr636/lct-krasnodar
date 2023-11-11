package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.courses.CourseDTO;
import com.kydas.lctkrasnodar.courses.CourseService;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.programs.Program;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CourseInitializer {
    private final CourseService courseService;

    public List<Course> createCourses(List<Program> programs, List<File> documents) throws IOException, ApiException {
        List<CourseDTO> courses = new ArrayList<>();

        courses.add(new CourseDTO()
            .setName("Пресейл (Presale)")
            .setDuration(10)
            .setFileId(documents.get(0).getId())
            .setProgramId(programs.get(0).getId())
        );

        courses.add(new CourseDTO()
            .setName("Чек-лист подготовки к встрече")
            .setDuration(5)
            .setFileId(documents.get(1).getId())
            .setProgramId(programs.get(0).getId())
        );

        courses.add(new CourseDTO()
            .setName("Принципы дизайна Proscom")
            .setDuration(7)
            .setFileId(documents.get(2).getId())
            .setProgramId(programs.get(1).getId())
        );

        courses.add(new CourseDTO()
            .setName("Работа с презентациями")
            .setDuration(5)
            .setFileId(documents.get(3).getId())
            .setProgramId(programs.get(1).getId())
        );

        courses.add(new CourseDTO()
            .setName("Код ревью")
            .setDuration(8)
            .setFileId(documents.get(4).getId())
            .setProgramId(programs.get(2).getId())
        );

        courses.add(new CourseDTO()
            .setName("Компетенции разработчиков")
            .setDuration(6)
            .setFileId(documents.get(5).getId())
            .setProgramId(programs.get(2).getId())
        );

        courses.add(new CourseDTO()
            .setName("Зона ответственности проектного менеджера")
            .setDuration(10)
            .setFileId(documents.get(6).getId())
            .setProgramId(programs.get(3).getId())
        );

        courses.add(new CourseDTO()
            .setName("Принципы ведения проектов и обязательные атрибуты")
            .setDuration(7)
            .setFileId(documents.get(7).getId())
            .setProgramId(programs.get(3).getId())
        );

        courses.add(new CourseDTO()
            .setName("FAQ")
            .setDuration(2)
            .setFileId(documents.get(8).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Onboarding")
            .setDuration(3)
            .setFileId(documents.get(9).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Welcome to the Proscom")
            .setDuration(1)
            .setFileId(documents.get(10).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Больничные, отпуска и удаленная работа")
            .setDuration(1)
            .setFileId(documents.get(11).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Деловой этикет онлайн и офлайн-встреч")
            .setDuration(2)
            .setFileId(documents.get(12).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Как устроена компания")
            .setDuration(2)
            .setFileId(documents.get(13).getId())
            .setProgramId(programs.get(4).getId())
        );
        courses.add(new CourseDTO()
            .setName("Манифест эмоционального состояния сотрудников")
            .setDuration(2)
            .setFileId(documents.get(14).getId())
            .setProgramId(programs.get(4).getId())
        );

        courses.add(new CourseDTO()
            .setName("Подружиться с Asana, узнать как это работает")
            .setDuration(5)
            .setFileId(documents.get(15).getId())
            .setProgramId(programs.get(5).getId())
        );
        courses.add(new CourseDTO()
            .setName("Познакомиться с Google Chat")
            .setDuration(2)
            .setFileId(documents.get(16).getId())
            .setProgramId(programs.get(5).getId())
        );
        courses.add(new CourseDTO()
            .setName("Познакомиться с нашим Boris Daily в Google Chat")
            .setDuration(3)
            .setFileId(documents.get(16).getId())
            .setProgramId(programs.get(5).getId())
        );
        courses.add(new CourseDTO()
            .setName("Получи первую задачу в проекте")
            .setDuration(2)
            .setFileId(documents.get(17).getId())
            .setProgramId(programs.get(5).getId())
        );

        List<Course> createdCourses = new ArrayList<>();
        for (CourseDTO courseDTO : courses) {
            createdCourses.add(courseService.create(courseDTO));
        }
        return createdCourses;
    }
}
