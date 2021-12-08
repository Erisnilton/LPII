package br.com.projeto.lp2.core.ports.driven.repository.service;

import br.com.projeto.lp2.core.domain.Service;

public interface ServiceRepositoryPort {
    Service save(Service service);
}
