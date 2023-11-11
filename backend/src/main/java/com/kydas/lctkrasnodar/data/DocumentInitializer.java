package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DocumentInitializer {
    private final FileService fileService;

    public List<File> createDocuments() throws IOException {
        List<File> documents = new ArrayList<>();
        String[] fileNames = {
            "Пресейл (Presale).pdf",
            "Чек-лист подготовки к встрече.pdf",
            "Принципы дизайна Proscom.pdf",
            "Работа с презентациями.pdf",
            "Код ревью.pdf",
            "Компетенции разработчиков.pdf",
            "Зона ответственности проектного менеджера.pdf",
            "Принципы ведения проектов и обязательные атрибуты.pdf",
            "FAQ.pdf",
            "Onboarding.pdf",
            "Welcome to the Proscom.pdf",
            "Больничные, отпуска и удаленная работа.pdf",
            "Деловой этикет онлайн и офлайн-встреч.pdf",
            "Как устроена компания.pdf",
            "Манифест эмоционального состояния сотрудников.pdf",
            "Подружиться с Asana, узнать как это работает.pdf",
            "Познакомиться с Google Chat.pdf",
            "Познакомиться с нашим Boris Daily в Google Chat.pdf",
            "Получи первую задачу в проекте.pdf",
        };

        for (String fileName: fileNames) {
            Resource resource = new ClassPathResource("pdf/" + fileName);
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();

            File file = fileService.uploadFile(fileName, bytes);
            documents.add(file);
        }
        return documents;
    }
}
