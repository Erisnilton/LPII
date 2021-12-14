package br.com.projeto.lp2.controller.schedule;

import br.com.projeto.lp2.controller.schedule.request.ScheduleRequest;
import br.com.projeto.lp2.controller.schedule.response.ScheduleRespose;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.ports.driver.schedule.CreateSchedulePort;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("schedules")
public record ScheduleController(CreateSchedulePort createSchedulePort) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleRespose post(@RequestBody @Valid ScheduleRequest request) {
        Schedule schedule = request.toSchedule();
        Schedule scheduleSaved = createSchedulePort.apply(schedule);
        return new ScheduleRespose().fromSchedule(scheduleSaved);
    }

    @GetMapping("user/{id}")
    public List<Schedule> getScheduleByUser(@PathVariable ObjectId id) {
      return createSchedulePort.findAll(id);
    }
}
