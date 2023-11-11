package com.kydas.lctkrasnodar.mailing;

import com.kydas.lctkrasnodar.files.FileDTO;
import com.kydas.lctkrasnodar.notification.Notification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class MailingDTO {
    private Long id;

    private String name;

    private String text;

    private FileDTO file;

    private Boolean isTemplate = false;

    private Boolean isRead = false;

    private List<String> departments = new ArrayList<>();

    public MailingDTO(Mailing mailing) {
        id = mailing.getId();
        name = mailing.getName();
        text = mailing.getText();
        file = mailing.getFile() != null ? new FileDTO(mailing.getFile()) : null;
        isTemplate = mailing.getIsTemplate();
        departments = mailing.getDepartments();
        isRead = mailing.getIsRead();
    }
}