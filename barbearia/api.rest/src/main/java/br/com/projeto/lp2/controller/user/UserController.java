package br.com.projeto.lp2.controller.user;

import br.com.projeto.lp2.controller.user.request.UserRequest;
import br.com.projeto.lp2.controller.user.response.UserResponse;
import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driver.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse post(@RequestBody @Valid UserRequest request) {
        var user = request.toUser();
        var userSaved = createUserPort.apply(user);
       return new UserResponse().fromUser(userSaved);
    }

    @GetMapping("{id}")
    public UserResponse getUserById(@PathVariable String id) {
        User user = getUserByIdPort.apply(id);
        return new UserResponse().fromUser(user);

    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        var users = getUsersPort.apply();
        return ResponseEntity.ok(users.stream().map(user -> new UserResponse().fromUser(user)).toList());
    }

    @PutMapping("{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody @Valid  UserRequest request) {
        User user = request.toUser();
        return new UserResponse().fromUser(upadateUser.apply(id, user));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        deleteUserPort.apply(id);
    }
}
