package br.com.projeto.lp2.controller;

import br.com.projeto.lp2.controller.request.UserRequest;
import br.com.projeto.lp2.controller.response.UserResponse;
import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driver.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public record UserController(
        CreateUserPort createUserPort,
        GetUserByIdPort getUserByIdPort,
        GetAllUsersPort getUsersPort,
        UpadateUserPort upadateUser,
        DeleteUserByIdPort deleteUserPort
)
{
    @PostMapping
    public UserResponse post(@RequestBody UserRequest request) {
        var user = request.toUser();
        User userSaved = createUserPort.apply(user);
        return new UserResponse().fromUser(userSaved);
    }

    @GetMapping("{id}")
    public UserResponse getUserById(@PathVariable String id) {
        User user = getUserByIdPort.apply(id);
        return new UserResponse().fromUser(user);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        var users = getUsersPort.apply();
        return users.stream().map(user -> new UserResponse().fromUser(user)).toList();
    }

    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        if(request == null || id == null) {
            throw new IllegalArgumentException("Erro ao atualizar o usuario");
        }
        User user = request.toUser();
        User userUpdated = upadateUser.apply(id, user);
        return new UserResponse().fromUser(userUpdated);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        if(id == null) {
            throw new IllegalArgumentException("Usuario Inexistente");
        }
        deleteUserPort.apply(id);
    }

}
