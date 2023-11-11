package com.kydas.lctkrasnodar.mailing;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private File file;

    private String text;

    private Boolean isTemplate = false;

    private Boolean isRead = false;

    @ElementCollection
    private List<String> departments = new ArrayList<>();
}
