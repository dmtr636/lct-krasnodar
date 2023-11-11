package com.kydas.lctkrasnodar.user;

import com.kydas.lctkrasnodar.core.exceptions.ApiException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.kydas.lctkrasnodar.core.request.Endpoints.USERS_ENDPOINT;

@RestController
@RequestMapping(USERS_ENDPOINT)
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> all() {
        List<User> users = service.getAllUsers();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) throws ApiException {
        User user = service.createUser(userDTO);
        return new UserDTO(user);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) throws ApiException {
        User user = service.getUserById(id);
        return new UserDTO(user);
    }

    @PutMapping
    public UserDTO update(@RequestBody @Valid UserDTO userDTO) throws ApiException {
        User user = service.updateUser(userDTO);
        return new UserDTO(user);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
