package br.com.projeto.lp2.core.us;

import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driven.repository.UserRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.GetUsersPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record getUsersUS(UserRepositoryPort repo ) implements GetUsersPort {
    @Override
    public User apply(String id) {
        return repo.findById(id).orElseThrow((() -> new IllegalArgumentException("id not found!")));
    }
}
