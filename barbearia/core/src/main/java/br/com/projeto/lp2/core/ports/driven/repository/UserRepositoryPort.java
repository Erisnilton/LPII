package br.com.projeto.lp2.core.ports.driven.repository;

import br.com.projeto.lp2.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findById(String id);
}
