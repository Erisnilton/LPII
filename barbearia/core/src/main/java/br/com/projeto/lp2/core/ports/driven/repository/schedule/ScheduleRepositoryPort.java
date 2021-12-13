package br.com.projeto.lp2.core.ports.driven.repository.schedule;

import br.com.projeto.lp2.core.domain.Schedule;

public interface ScheduleRepositoryPort {
    Schedule save(Schedule schedule);
}
