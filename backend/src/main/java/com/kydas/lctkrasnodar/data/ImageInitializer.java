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
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class ImageInitializer {
    private final FileService fileService;

    public List<File> createImages() throws IOException {
        List<File> images = new ArrayList<>();
        int[] indexes = IntStream.range(1, 24 + 1).toArray();

        for (int index: indexes) {
            String fileName = "photo" + index + ".png";
            Resource resource = new ClassPathResource("images/" + fileName);
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();

            File file = fileService.uploadFile(fileName, bytes);
            images.add(file);
        }
        return images;
    }
}
