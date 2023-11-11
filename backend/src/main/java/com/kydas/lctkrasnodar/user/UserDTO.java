package com.kydas.lctkrasnodar.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private User.Department department;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    private String patronymic;

    @NotBlank
    private String telegram;

    @NotBlank
    private String phone;

    private Long responsibleUserId;

    private List<String> skills = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User.Role role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fullName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String photoFileUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createTimestamp;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID photoFileId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileDTO photoFile;

    public static UserDTO withDefaults() {
        return new UserDTO()
            .setLastName("")
            .setFirstName("")
            .setPatronymic("")
            .setTelegram("")
            .setPhone("");
    }

    public UserDTO(User user) {
        id = user.getId();
        email = user.getEmail();
        role = user.getRole();
        lastName = user.getLastName();
        firstName = user.getFirstName();
        patronymic = user.getPatronymic();
        fullName = user.getFullName();
        photoFileUrl = user.getPhotoFileUrl();
        telegram = user.getTelegram();
        department = user.getDepartment();
        phone = user.getPhone();
        createTimestamp = user.getCreateTimestamp().toString();
        responsibleUserId = user.getResponsibleUserId();
        photoFile = user.getPhotoFile() != null ? new FileDTO(user.getPhotoFile()) : null;
        skills = user.getSkills();
    }
}