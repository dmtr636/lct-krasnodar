package com.kydas.lctkrasnodar.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kydas.lctkrasnodar.files.FileDTO;
import com.kydas.lctkrasnodar.mailing.Mailing;
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
public class MessageDTO {
    private Long id;

    private String name;

    private String text;

    private FileDTO file;

    private Boolean isRead = false;

    private String creationTimestamp;

    private Long userId;

    public MessageDTO(Message message) {
        id = message.getId();
        name = message.getName();
        text = message.getText();
        file = message.getFile() != null ? new FileDTO(message.getFile()) : null;
        isRead = message.getIsRead();
        creationTimestamp = message.getCreationTimestamp().toString();
        userId = message.getUser().getId();
    }
}