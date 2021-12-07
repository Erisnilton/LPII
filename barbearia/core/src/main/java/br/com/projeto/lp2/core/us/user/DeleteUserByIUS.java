package br.com.projeto.lp2.core.us.user;

import br.com.projeto.lp2.core.ports.driven.repository.UserRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.user.DeleteUserByIdPort;
import org.springframework.stereotype.Service;

@Service
public record DeleteUserByIUS(UserRepositoryPort repository) implements DeleteUserByIdPort {
    @Override
    public void apply(String id) {
        if(id == null) {
            throw new IllegalArgumentException("Usuario com esse " + id + "não encontrado");
        }
        repository.deleteById(id);
    }
}
