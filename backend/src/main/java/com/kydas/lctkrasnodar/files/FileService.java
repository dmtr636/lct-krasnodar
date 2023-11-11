package com.kydas.lctkrasnodar.files;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    public File uploadFile(MultipartFile multipartFile) throws IOException {
        return saveFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());
    }

    public File uploadFile(String fileName, byte[] bytes) throws IOException {
        return saveFile(fileName, bytes);
    }

    public File getById(UUID fileId) throws ApiException {
        return fileRepository.findById(fileId).orElseThrow(NotFoundException::new);
    }

    public ByteArrayResource convertToResource(File file) {
        return new ByteArrayResource(file.getBytes());
    }

    public MediaType getContentType(File file) {
        String mimeType = URLConnection.guessContentTypeFromName(file.getFileName());
        return MediaType.parseMediaType(mimeType);
    }

    private File saveFile(String fileName, byte[] bytes) {
        File file = new File();
        file.setFileName(fileName);
        file.setBytes(bytes);
        return fileRepository.save(file);
    }
}
