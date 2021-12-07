package br.com.projeto.lp2.controller;

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
    public User post(@RequestBody User user) {
        return createUserPort.apply(user);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) {
        return getUserByIdPort.apply(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return getUsersPort.apply();
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        if(user == null || id == null) {
            throw new IllegalArgumentException("Erro ao atualizar o usuario");
        }
        return upadateUser.apply(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        if(id == null) {
            throw new IllegalArgumentException("Usuario Inexistente");
        }
        deleteUserPort.apply(id);
    }

}
