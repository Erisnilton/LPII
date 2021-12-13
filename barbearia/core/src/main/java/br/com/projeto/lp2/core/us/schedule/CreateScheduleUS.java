package br.com.projeto.lp2.core.us.schedule;

import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.ports.driver.schedule.CreateSchedulePort;

public record CreateScheduleUS() implements CreateSchedulePort {
    @Override
    public Schedule apply(Schedule schedule) {
        return null;
    }
}
