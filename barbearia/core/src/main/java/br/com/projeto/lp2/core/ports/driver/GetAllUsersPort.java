package br.com.projeto.lp2.core.ports.driver;

import br.com.projeto.lp2.core.domain.User;

import java.util.List;

public interface GetAllUsersPort {
    List<User> apply();
}
