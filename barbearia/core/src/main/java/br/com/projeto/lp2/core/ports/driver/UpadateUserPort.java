package br.com.projeto.lp2.core.ports.driver;

import br.com.projeto.lp2.core.domain.User;

import java.util.Optional;


public interface UpadateUserPort {
    Optional<User> apply(String id, User user);
}
