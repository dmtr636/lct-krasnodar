package com.kydas.lctkrasnodar.files;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.kydas.lctkrasnodar.core.request.Endpoints.FILES_ENDPOINT;

@RequiredArgsConstructor
@RestController
@RequestMapping(FILES_ENDPOINT)
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public FileDTO upload(@RequestParam MultipartFile file) throws IOException {
        File uploadedFile = fileService.uploadFile(file);
        return new FileDTO(uploadedFile);
    }

    @GetMapping(value = "/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadById(@PathVariable UUID fileId) throws ApiException {
        File file = fileService.getById(fileId);
        MediaType contentType = fileService.getContentType(file);
        ByteArrayResource resource = fileService.convertToResource(file);
        String contentDispositionHeader = "attachment; filename=\"" + file.getFileName() + "\"";

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeader)
            .contentType(contentType)
            .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
            .body(resource);
    }
}
