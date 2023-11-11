package com.kydas.lctkrasnodar.messages;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private File file;

    private String text;

    private Boolean isRead = false;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Instant creationTimestamp;
}
