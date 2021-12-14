package br.com.projeto.lp2.controller.schedule;

import br.com.projeto.lp2.controller.schedule.request.ScheduleRequest;
import br.com.projeto.lp2.controller.schedule.response.ScheduleRespose;
import br.com.projeto.lp2.controller.user.request.UserRequest;
import br.com.projeto.lp2.controller.user.response.UserResponse;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.domain.User;
import br.com.projeto.lp2.core.ports.driver.schedule.*;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("schedules")
public record ScheduleController(
        CreateSchedulePort createSchedulePort,
        GetScheduleByUserPort getScheduleByUserPort,
        GetScheduleByIdPort getScheduleByIdPort,
        UpdateSchedulePort upadateSchedulePort,
        DeleteSchedulePort deleteSchedulePort
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleRespose post(@RequestBody @Valid ScheduleRequest request) {
        Schedule schedule = request.toSchedule();
        Schedule scheduleSaved = createSchedulePort.apply(schedule);
        return new ScheduleRespose().fromSchedule(scheduleSaved);
    }

    @GetMapping("user/{id}")
    public List<Schedule> getScheduleByUser(@PathVariable ObjectId id) {
      return getScheduleByUserPort.aplly(id);
    }

    @GetMapping("{id}")
    public ScheduleRespose getSchedule(@PathVariable String id){
        Schedule schedule = getScheduleByIdPort.apply(id);
        return new ScheduleRespose().fromSchedule(schedule);
    }

    @PutMapping("{id}")
    public ScheduleRespose updateSchedule(@PathVariable String id, @RequestBody @Valid ScheduleRequest request) {
        var schedule = request.toSchedule();
        return new ScheduleRespose().fromSchedule(upadateSchedulePort.apply(id,schedule));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable String id) {
        deleteSchedulePort.apply(id);
    }

}
