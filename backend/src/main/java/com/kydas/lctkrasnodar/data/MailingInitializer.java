package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.mailing.Mailing;
import com.kydas.lctkrasnodar.mailing.MailingDTO;
import com.kydas.lctkrasnodar.mailing.MailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MailingInitializer {
    private final MailingService mailingService;

    public List<Mailing> createMailings() throws IOException, ApiException {
        List<MailingDTO> mailings = new ArrayList<>();

        mailings.add(new MailingDTO()
            .setName("Поздравление с новым годом")
            .setText("С новым годом")
            .setIsTemplate(true)
        );

        List<Mailing> createdMailings = new ArrayList<>();
        for (MailingDTO mailingDTO : mailings) {
            createdMailings.add(mailingService.create(mailingDTO));
        }
        return createdMailings;
    }
}
