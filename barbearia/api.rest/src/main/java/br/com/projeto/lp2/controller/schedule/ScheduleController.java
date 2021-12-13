package br.com.projeto.lp2.controller.schedule;

import br.com.projeto.lp2.controller.schedule.request.ScheduleResquest;
import br.com.projeto.lp2.controller.schedule.response.ScheduleRespose;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.ports.driver.schedule.CreateSchedulePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("schedule")
public record ScheduleController(CreateSchedulePort createSchedulePort) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleRespose post(@RequestBody @Valid ScheduleResquest resquest) {
        Schedule schedule = resquest.toSchedule();
        Schedule scheduleSaved = createSchedulePort.apply(schedule);
        return new ScheduleRespose().fromSchedule(scheduleSaved);
    }
}
