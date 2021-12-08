package br.com.projeto.lp2.core.us.service;

import br.com.projeto.lp2.core.domain.Service;
import br.com.projeto.lp2.core.ports.driven.repository.service.ServiceRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.service.UpdateServicePort;

import java.util.Optional;

@org.springframework.stereotype.Service
public record UpdateServiceUS(ServiceRepositoryPort repository) implements UpdateServicePort {
    @Override
    public Optional<Service> apply(String id, Service service) {
        if (service == null || id == null) {
            throw new IllegalArgumentException("Erro ao atualizar o usuário");
        }

        Optional<Service> userForUpdate = repository.findById(id);
        if (userForUpdate.isPresent()) {
            Service serviceUpdating = userForUpdate.get();
            serviceUpdating.setName(service.getName());
            serviceUpdating.setPrice(service.getPrice());
            serviceUpdating.setDuration(service.getDuration());

            return Optional.ofNullable(repository.save(serviceUpdating));

        }
        return  Optional.empty();
    }
}
