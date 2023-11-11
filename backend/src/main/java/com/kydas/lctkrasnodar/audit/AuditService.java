package com.kydas.lctkrasnodar.audit;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import com.kydas.lctkrasnodar.core.security.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditEventRepository auditRepository;
    private final SecurityContext securityContext;

    public List<AuditEvent> getAllEvents() {
        return auditRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void addEvent(AuditEvent.EventType eventType) throws ApiException {
        AuditEvent auditEvent = new AuditEvent();
        auditEvent.setEventType(eventType);
        auditEvent.setUser(securityContext.getCurrentUser());
        auditRepository.save(auditEvent);
    }

    public void addEvent(AuditEvent.EventType eventType, String text) throws ApiException {
        AuditEvent auditEvent = new AuditEvent();
        auditEvent.setEventType(eventType);
        auditEvent.setText(text);
        auditEvent.setUser(securityContext.getCurrentUser());
        auditRepository.save(auditEvent);
    }
}
