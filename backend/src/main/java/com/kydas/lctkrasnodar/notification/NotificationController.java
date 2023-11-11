package com.kydas.lctkrasnodar.notification;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService service;

    @GetMapping
    public List<NotificationDTO> all() throws ApiException {
        List<Notification> notifications = service.getAll();
        return notifications.stream().map(NotificationDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public NotificationDTO create(@RequestBody @Valid NotificationDTO notificationDTO) throws ApiException {
        return new NotificationDTO(service.create(notificationDTO));
    }

    @GetMapping("/{id}")
    public NotificationDTO getById(@PathVariable Long id) throws ApiException {
        return new NotificationDTO(service.getById(id));
    }

    @PutMapping
    public NotificationDTO update(@RequestBody @Valid NotificationDTO notificationDTO) throws ApiException {
        return new NotificationDTO(service.update(notificationDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
