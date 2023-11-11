package com.kydas.lctkrasnodar.mailing;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.mailing.Mailing;
import com.kydas.lctkrasnodar.mailing.MailingDTO;
import com.kydas.lctkrasnodar.mailing.MailingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mailings")
public class MailingController {
    private final MailingService service;

    @GetMapping
    public List<MailingDTO> all() throws ApiException {
        List<Mailing> mailings = service.getAll();
        return mailings.stream().map(MailingDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public MailingDTO create(@RequestBody @Valid MailingDTO mailingDTO) throws ApiException {
        return new MailingDTO(service.create(mailingDTO));
    }

    @GetMapping("/{id}")
    public MailingDTO getById(@PathVariable Long id) throws ApiException {
        return new MailingDTO(service.getById(id));
    }

    @PutMapping
    public MailingDTO update(@RequestBody @Valid MailingDTO mailingDTO) throws ApiException {
        return new MailingDTO(service.update(mailingDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
