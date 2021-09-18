package br.edu.ifce.lp2.model.services;

import br.edu.ifce.lp2.model.entities.User;
import br.edu.ifce.lp2.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void create(User user) {
        repository.save(user);
    }

}
