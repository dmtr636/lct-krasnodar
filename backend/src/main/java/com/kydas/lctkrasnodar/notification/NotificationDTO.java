package com.kydas.lctkrasnodar.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NotificationDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotBlank
    private String text;

    private Notification.Type type;

    private Boolean isRead = false;

    private String url;

    public NotificationDTO(Notification notification) {
        id = notification.getId();
        userId = notification.getUserId();
        text = notification.getText();
        type = notification.getType();
        isRead = notification.getIsRead();
        url = notification.getUrl();
    }
}