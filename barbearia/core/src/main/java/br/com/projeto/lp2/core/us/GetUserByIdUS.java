package br.com.projeto.lp2.core.us;

import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driven.repository.UserRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.GetUserByIdPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record GetUserByIdUS(UserRepositoryPort repo ) implements GetUserByIdPort {
    @Override
    public Optional<User> apply(String id) {
        return repo.findById(id);
    }
}
