package br.com.projeto.lp2.core.us.service;

import br.com.projeto.lp2.core.domain.Service;
import br.com.projeto.lp2.core.ports.driven.repository.service.ServiceRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.service.GetServiceByIdPort;

import java.util.Optional;

@org.springframework.stereotype.Service
public record GetServiceByIdUS(ServiceRepositoryPort repository) implements GetServiceByIdPort {
    @Override
    public Optional<Service> apply(String id) {
        return repository.findById(id);
    }
}
