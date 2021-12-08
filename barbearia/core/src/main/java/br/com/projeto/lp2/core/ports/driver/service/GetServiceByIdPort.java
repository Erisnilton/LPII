package br.com.projeto.lp2.core.ports.driver.service;

import br.com.projeto.lp2.core.domain.Service;

import java.util.Optional;

public interface GetServiceByIdPort {
    Optional<Service> apply(String id);
}
