package br.edu.ifce.lp2.controller;

import br.edu.ifce.lp2.model.entities.User;
import br.edu.ifce.lp2.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void post(@RequestBody User user) {
        userService.create(user);
    }

}
