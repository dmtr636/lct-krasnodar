package com.kydas.lctkrasnodar.data;

import com.kydas.lctkrasnodar.files.File;
import com.kydas.lctkrasnodar.files.FileService;
import com.kydas.lctkrasnodar.user.User;
import com.kydas.lctkrasnodar.user.UserDTO;
import com.kydas.lctkrasnodar.user.UserRepository;
import com.kydas.lctkrasnodar.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserInitializer {
    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUserEmail;

    @Value("${spring.security.user.password}")
    private String adminUserPassword;

    public boolean isAdminUserExists() {
        return userRepository.existsByEmail(adminUserEmail);
    }

    public List<User> createUsers(List<File> images) throws IOException {
        List<UserDTO> users = new ArrayList<>();

        UserDTO admin = UserDTO.withDefaults()
            .setEmail(adminUserEmail)
            .setPassword(adminUserPassword)
            .setRole(User.Role.ADMIN)
            .setDepartment(User.Department.ADMIN);
        users.add(admin);

        UserDTO u1 = UserDTO.withDefaults()
            .setEmail("hr1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.HR)
            .setLastName("Иванов")
            .setFirstName("Александр")
            .setPatronymic("Игоревич")
            .setTelegram("BlueSkyExplorer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(0).getId())
            .setSkills(List.of("Scrum", "Agile"));
        users.add(u1);

        UserDTO u2 = UserDTO.withDefaults()
            .setEmail("hr2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.HR)
            .setLastName("Игнатьева")
            .setFirstName("Анастасия")
            .setPatronymic("Игоревна")
            .setTelegram("QuantumJellyfish")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(1).getId())
            .setSkills(List.of("Scrum", "Agile"));
        users.add(u2);

        UserDTO u3 = UserDTO.withDefaults()
            .setEmail("manager1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.MANAGER)
            .setLastName("Петров")
            .setFirstName("Дмитрий")
            .setPatronymic("Сергеевич")
            .setTelegram("CosmicPenguin")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(2).getId())
            .setSkills(List.of("Scrum", "Agile"));
        users.add(u3);

        UserDTO u4 = UserDTO.withDefaults()
            .setEmail("manager2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.MANAGER)
            .setLastName("Петровская")
            .setFirstName("Екатерина")
            .setPatronymic("Петровна")
            .setTelegram("VelvetThunder")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(3).getId())
            .setSkills(List.of("Scrum", "Agile"));
        users.add(u4);

        UserDTO u5 = UserDTO.withDefaults()
            .setEmail("client.service1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.CLIENT_SERVICE)
            .setLastName("Смирнов")
            .setFirstName("Максим")
            .setPatronymic("Александрович")
            .setTelegram("LunarWhisperer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(4).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(2L);
        users.add(u5);

        UserDTO u6 = UserDTO.withDefaults()
            .setEmail("project.manager1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.PROJECT_MANAGEMENT)
            .setLastName("Кузнецов")
            .setFirstName("Артем")
            .setPatronymic("Андреевич")
            .setTelegram("ElectricZephyr")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(5).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(3L);
        users.add(u6);

        UserDTO u7 = UserDTO.withDefaults()
            .setEmail("designer1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DESIGN)
            .setLastName("Семенова")
            .setFirstName("Виктория")
            .setPatronymic("Сергеевна")
            .setTelegram("NebulaNomad")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(6).getId())
            .setSkills(List.of("Figma", "HTML", "CSS"))
            .setResponsibleUserId(4L);
        users.add(u7);

        UserDTO u8 = UserDTO.withDefaults()
            .setEmail("developer1@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DEVELOPMENT)
            .setLastName("Морозов")
            .setFirstName("Егор")
            .setPatronymic("Николаевич")
            .setTelegram("MidnightSpecter")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(7).getId())
            .setSkills(List.of("Python", "JavaScript", "Java"))
            .setResponsibleUserId(5L);
        users.add(u8);

        UserDTO u9 = UserDTO.withDefaults()
            .setEmail("client.service2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.CLIENT_SERVICE)
            .setLastName("Горбачева")
            .setFirstName("Мария")
            .setPatronymic("Андреевна")
            .setTelegram("EnigmaticQuasar")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(8).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(2L);
        users.add(u9);

        UserDTO u10 = UserDTO.withDefaults()
            .setEmail("project.manager2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.PROJECT_MANAGEMENT)
            .setLastName("Козлов")
            .setFirstName("Иван")
            .setPatronymic("Дмитриевич")
            .setTelegram("SolarFlareSeeker")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(9).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(3L);
        users.add(u10);

        UserDTO u11 = UserDTO.withDefaults()
            .setEmail("designer2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DESIGN)
            .setLastName("Васильев")
            .setFirstName("Никита")
            .setPatronymic("Алексеевич")
            .setTelegram("CelestialHarmony")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(10).getId())
            .setSkills(List.of("Figma", "HTML", "CSS"))
            .setResponsibleUserId(4L);
        users.add(u11);

        UserDTO u12 = UserDTO.withDefaults()
            .setEmail("developer2@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DEVELOPMENT)
            .setLastName("Николаева")
            .setFirstName("Ольга")
            .setPatronymic("Николаевна")
            .setTelegram("StealthyStardust")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(11).getId())
            .setSkills(List.of("C++", "React", "Angular"))
            .setResponsibleUserId(5L);
        users.add(u12);

        UserDTO u13 = UserDTO.withDefaults()
            .setEmail("client.service3@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.CLIENT_SERVICE)
            .setLastName("Васнецова")
            .setFirstName("Алина")
            .setPatronymic("Дмитриевна")
            .setTelegram("RadiantAurora")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(12).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(2L);
        users.add(u13);

        UserDTO u14 = UserDTO.withDefaults()
            .setEmail("project.manager3@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.PROJECT_MANAGEMENT)
            .setLastName("Соколов")
            .setFirstName("Станислав")
            .setPatronymic("Владимирович")
            .setTelegram("GravityPioneer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(13).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(3L);
        users.add(u14);

        UserDTO u15 = UserDTO.withDefaults()
            .setEmail("designer3@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DESIGN)
            .setLastName("Кузнецова")
            .setFirstName("Надежда")
            .setPatronymic("Алексеевна")
            .setTelegram("TechnoTrailblazer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(14).getId())
            .setSkills(List.of("Figma", "HTML", "CSS"))
            .setResponsibleUserId(4L);
        users.add(u15);

        UserDTO u16 = UserDTO.withDefaults()
            .setEmail("developer3@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DEVELOPMENT)
            .setLastName("Тимофеева")
            .setFirstName("Юлия")
            .setPatronymic("Александровна")
            .setTelegram("AstralAdventurer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(15).getId())
            .setSkills(List.of("Vue.js", "MySQL", "PostgreSQL"))
            .setResponsibleUserId(5L);
        users.add(u16);

        UserDTO u17 = UserDTO.withDefaults()
            .setEmail("client.service4@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.CLIENT_SERVICE)
            .setLastName("Новиков")
            .setFirstName("Павел")
            .setPatronymic("Олегович")
            .setTelegram("MysticMango")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(16).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(2L);
        users.add(u17);

        UserDTO u18 = UserDTO.withDefaults()
            .setEmail("project.manager4@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.PROJECT_MANAGEMENT)
            .setLastName("Соколова")
            .setFirstName("Елена")
            .setPatronymic("Владимировна")
            .setTelegram("PixelPioneer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(17).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(3L);
        users.add(u18);

        UserDTO u19 = UserDTO.withDefaults()
            .setEmail("designer4@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DESIGN)
            .setLastName("Федоров")
            .setFirstName("Владислав")
            .setPatronymic("Анатольевич")
            .setTelegram("StarlightSurfer")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(18).getId())
            .setSkills(List.of("Figma", "HTML", "CSS"))
            .setResponsibleUserId(4L);
        users.add(u19);

        UserDTO u20 = UserDTO.withDefaults()
            .setEmail("developer4@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DEVELOPMENT)
            .setLastName("Беляева")
            .setFirstName("Анна")
            .setPatronymic("Ивановна")
            .setTelegram("SonicSerpent")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(19).getId())
            .setSkills(List.of("Google Cloud", "AWS", "JavaScript"))
            .setResponsibleUserId(5L);
        users.add(u20);

        UserDTO u21 = UserDTO.withDefaults()
            .setEmail("client.service5@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.CLIENT_SERVICE)
            .setLastName("Миронова")
            .setFirstName("Светлана")
            .setPatronymic("Валентиновна")
            .setTelegram("EchoEagle")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(20).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(2L);
        users.add(u21);

        UserDTO u22 = UserDTO.withDefaults()
            .setEmail("project.manager5@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.PROJECT_MANAGEMENT)
            .setLastName("Егоров")
            .setFirstName("Антон")
            .setPatronymic("Валентинович")
            .setTelegram("CipherPhoenix")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(21).getId())
            .setSkills(List.of("Scrum", "Agile"))
            .setResponsibleUserId(3L);
        users.add(u22);

        UserDTO u23 = UserDTO.withDefaults()
            .setEmail("designer5@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DESIGN)
            .setLastName("Соловьев")
            .setFirstName("Юрий")
            .setPatronymic("Анатольевич")
            .setTelegram("GalacticGazelle")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(22).getId())
            .setSkills(List.of("Figma", "HTML", "CSS"))
            .setResponsibleUserId(4L);
        users.add(u23);

        UserDTO u24 = UserDTO.withDefaults()
            .setEmail("developer5@hack-kydas.ru")
            .setPassword("admin")
            .setDepartment(User.Department.DEVELOPMENT)
            .setLastName("Федорова")
            .setFirstName("Татьяна")
            .setPatronymic("Анатольевна")
            .setTelegram("ZenithZebra")
            .setPhone("+79101234567")
            .setPhotoFileId(images.get(23).getId())
            .setSkills(List.of("Java", "Angular", "React"))
            .setResponsibleUserId(5L);
        users.add(u24);

        List<User> createdUsers = new ArrayList<>();

        for (UserDTO user : users) {
            try {
                createdUsers.add(userService.createUser(user));
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        return createdUsers;
    }
}
