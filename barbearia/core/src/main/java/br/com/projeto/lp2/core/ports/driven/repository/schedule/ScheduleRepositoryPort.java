package br.com.projeto.lp2.core.ports.driven.repository.schedule;

import br.com.projeto.lp2.core.domain.Schedule;

import java.util.List;

public interface ScheduleRepositoryPort {
    Schedule save(Schedule schedule);

    List<Schedule> findAll();
}
