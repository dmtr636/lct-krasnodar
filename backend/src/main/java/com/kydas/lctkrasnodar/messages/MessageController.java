package com.kydas.lctkrasnodar.messages;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService service;

    @GetMapping
    public List<MessageDTO> all() throws ApiException {
        List<Message> messages = service.getAll();
        return messages.stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public MessageDTO create(@RequestBody @Valid MessageDTO messageDTO) throws ApiException {
        return new MessageDTO(service.create(messageDTO));
    }

    @GetMapping("/{id}")
    public MessageDTO getById(@PathVariable Long id) throws ApiException {
        return new MessageDTO(service.getById(id));
    }

    @PutMapping
    public MessageDTO update(@RequestBody @Valid MessageDTO messageDTO) throws ApiException {
        return new MessageDTO(service.update(messageDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
