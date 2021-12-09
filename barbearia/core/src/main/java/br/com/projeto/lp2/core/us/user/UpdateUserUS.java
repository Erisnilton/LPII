package br.com.projeto.lp2.core.us.user;

import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driven.repository.user.UserRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.user.UpadateUserPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UpdateUserUS(UserRepositoryPort repository) implements UpadateUserPort {
    @Override
    public Optional<User> apply(String id, User user) {

        Optional<User> userForUpdate = repository.findById(id);
        if (userForUpdate.isPresent()) {
            User userUpdating = userForUpdate.get();
            userUpdating.setName(user.getName());
            userUpdating.setEmail(user.getEmail());
            userUpdating.setAddress(user.getAddress());
            userUpdating.setPassword(user.getPassword());

            return Optional.ofNullable(repository.save(userUpdating));

        }
        return  Optional.empty();
    }
}
