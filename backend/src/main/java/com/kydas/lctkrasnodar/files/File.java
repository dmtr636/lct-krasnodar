package com.kydas.lctkrasnodar.files;

import com.kydas.lctkrasnodar.courses.Course;
import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.kydas.lctkrasnodar.core.request.Endpoints.FILES_ENDPOINT;

@Getter
@Setter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(nullable = false)
    private byte[] bytes;

    @OneToOne(mappedBy = "photoFile")
    private User user;

    public String getUrl() {
        return FILES_ENDPOINT + "/" + id;
    }
}
