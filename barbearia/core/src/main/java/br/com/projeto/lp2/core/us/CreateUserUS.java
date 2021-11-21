package br.com.projeto.lp2.core.us;

import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driven.repository.UserRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.CreateUserPort;

public record CreateUserUS (UserRepositoryPort repository ) implements CreateUserPort {

    @Override
    public User apply(User user) {
        return repository.save(user);
    }
}

