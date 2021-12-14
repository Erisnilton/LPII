package br.com.projeto.lp2.core.us.schedule;

import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.ports.driven.repository.schedule.ScheduleRepositoryPort;
import br.com.projeto.lp2.core.ports.driver.schedule.CreateSchedulePort;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CreateScheduleUS(
        ScheduleRepositoryPort repository
) implements CreateSchedulePort {
    @Override
    public Schedule apply(Schedule schedule) {
        // TODO - Ver regras de negocios  para criar um agendamento.
//        LocalDateTime agora = LocalDateTime.now();
//        schedule.setTime(agora);
        return repository.save(schedule);
    }

    @Override
    public List<Schedule> findAll(ObjectId id) {
        return repository.findAll()
                .stream()
                .filter(schedule -> schedule.getUser().equals(id)).collect(Collectors.toList());
    }
}
