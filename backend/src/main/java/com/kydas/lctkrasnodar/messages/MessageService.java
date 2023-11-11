package com.kydas.lctkrasnodar.messages;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileRepository;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Message> getAll() throws ApiException {
        return messageRepository.findAll();
    }

    @Transactional
    public Message getById(Long id) throws ApiException {
        return messageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Message create(MessageDTO courseDTO) throws ApiException {
        Message message = new Message();
        setFields(message, courseDTO);

        return messageRepository.save(message);
    }

    public Message update(MessageDTO messageDTO) throws ApiException {
        Long id = messageDTO.getId();
        Message course = messageRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(course, messageDTO);
        return messageRepository.save(course);
    }

    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    private void setFields(Message message, MessageDTO messageDTO) throws RelatedObjectNotFoundException {
        File file = getFile(messageDTO);
        User user = getUser(messageDTO);

        message
            .setName(messageDTO.getName())
            .setText(messageDTO.getText())
            .setFile(file)
            .setIsRead(messageDTO.getIsRead())
            .setUser(user);
    }

    private File getFile(MessageDTO messageDTO) throws RelatedObjectNotFoundException {
        UUID id = messageDTO.getFile() != null ? messageDTO.getFile().getId() : null;
        if (id == null) {
            return null;
        }
        return fileRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("file"));
    }

    private User getUser(MessageDTO messageDTO) throws RelatedObjectNotFoundException {
        Long id = messageDTO.getUserId();
        if (id == null) {
            return null;
        }
        return userRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("userId"));
    }
}
