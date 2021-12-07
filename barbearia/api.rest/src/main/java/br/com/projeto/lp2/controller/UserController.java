package br.com.projeto.lp2.controller;

import br.com.projeto.lp2.controller.request.UserRequest;
import br.com.projeto.lp2.controller.response.UserResponse;
import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driver.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

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
    public ResponseEntity<UserResponse> post(@RequestBody UserRequest request, HttpServletResponse response) {
        var user = request.toUser();
        var userSaved = createUserPort.apply(user);
        var userResponse = new UserResponse().fromUser(userSaved);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/id}").buildAndExpand(userSaved.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        User user = getUserByIdPort.apply(id);
        return ResponseEntity.ok(new UserResponse().fromUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var users = getUsersPort.apply();
        return ResponseEntity.ok(users.stream().map(user -> new UserResponse().fromUser(user)).toList());
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest request) {
        if(request == null || id == null) {
            throw new IllegalArgumentException("Erro ao atualizar o usuario");
        }
        User user = request.toUser();
        User userUpdated = upadateUser.apply(id, user);
        return ResponseEntity.ok(new UserResponse().fromUser(userUpdated));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        if(id == null) {
            throw new IllegalArgumentException("Usuario Inexistente");
        }
        deleteUserPort.apply(id);
    }
}
