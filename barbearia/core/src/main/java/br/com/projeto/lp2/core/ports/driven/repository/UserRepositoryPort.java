package br.com.projeto.lp2.core.ports.driven.repository;

import br.com.projeto.lp2.core.domain.User;

public interface UserRepositoryPort {
    User save(User user);
}
