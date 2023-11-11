package com.kydas.lctkrasnodar.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AuditEventDTO {
    private Long id;
    private String timestamp;
    private AuditEvent.EventType eventType;
    private Long userId;
    private String text;

    public AuditEventDTO(AuditEvent auditEvent) {
        this.id = auditEvent.getId();
        this.timestamp = auditEvent.getTimestamp().toString();
        this.eventType = auditEvent.getEventType();
        this.userId = auditEvent.getUser().getId();
        this.text = auditEvent.getText();
    }
}
