package com.kydas.lctkrasnodar.mailing;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.exceptions.NotFoundException;
import com.kydas.lctkrasnodar.core.exceptions.RelatedObjectNotFoundException;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileRepository;
import com.kydas.lctkrasnodar.mailing.Mailing;
import com.kydas.lctkrasnodar.mailing.MailingDTO;
import com.kydas.lctkrasnodar.mailing.MailingRepository;
import com.kydas.lctkrasnodar.messages.MessageService;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailingService {
    private final MailingRepository mailingRepository;
    private final FileRepository fileRepository;
    private final MessageService messageService;

    @Transactional
    public List<Mailing> getAll() throws ApiException {
        return mailingRepository.findAll();
    }

    @Transactional
    public Mailing getById(Long id) throws ApiException {
        return mailingRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Mailing create(MailingDTO courseDTO) throws ApiException {
        Mailing mailing = new Mailing();
        setFields(mailing, courseDTO);

        return mailingRepository.save(mailing);
    }

    public Mailing update(MailingDTO mailingDTO) throws ApiException {
        Long id = mailingDTO.getId();
        Mailing course = mailingRepository.findById(id).orElseThrow(NotFoundException::new);
        setFields(course, mailingDTO);
        return mailingRepository.save(course);
    }

    public void delete(Long id) {
        mailingRepository.deleteById(id);
    }

    private void setFields(Mailing mailing, MailingDTO mailingDTO) throws RelatedObjectNotFoundException {
        File file = getFile(mailingDTO);

        mailing
            .setName(mailingDTO.getName())
            .setText(mailingDTO.getText())
            .setFile(file)
            .setDepartments(mailingDTO.getDepartments())
            .setIsTemplate(mailingDTO.getIsTemplate())
            .setIsRead(mailingDTO.getIsRead());
    }

    private File getFile(MailingDTO mailingDTO) throws RelatedObjectNotFoundException {
        UUID id = mailingDTO.getFile() != null ? mailingDTO.getFile().getId() : null;
        if (id == null) {
            return null;
        }
        return fileRepository
            .findById(id)
            .orElseThrow(() -> new RelatedObjectNotFoundException("file"));
    }
}
