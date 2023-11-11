package com.kydas.lctkrasnodar.files;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FileDTO {
    private UUID id;
    private String url;
    private String name;

    public FileDTO(File file) {
        this.id = file.getId();
        this.url = file.getUrl();
        this.name = file.getFileName();
    }
}
