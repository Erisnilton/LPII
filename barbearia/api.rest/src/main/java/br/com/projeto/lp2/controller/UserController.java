package br.com.projeto.lp2.controller;

import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driver.CreateUserPort;
import br.com.projeto.lp2.core.ports.driver.GetUsersPort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public record UserController(
        CreateUserPort createUserPort,
        GetUsersPort getUsersPort
)
{
    @PostMapping
    public User post(@RequestBody User user) {
        System.out.println(user.getName());
        return createUserPort.apply(user);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) {
        System.out.println("aqui!1");
        return getUsersPort.apply(id);
    }

}
