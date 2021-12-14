package br.com.projeto.lp2.controller.schedule;

import br.com.projeto.lp2.controller.schedule.request.ScheduleRequest;
import br.com.projeto.lp2.controller.schedule.response.ScheduleRespose;
import br.com.projeto.lp2.controller.service.response.ServiceResponse;
import br.com.projeto.lp2.controller.user.response.UserResponse;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.ports.driver.schedule.*;
import br.com.projeto.lp2.core.ports.driver.service.GetServiceByIdPort;
import br.com.projeto.lp2.core.ports.driver.user.GetUserByIdPort;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("schedules")
public record ScheduleController(
        CreateSchedulePort createSchedulePort,
        GetScheduleByUserPort getScheduleByUserPort,
        GetScheduleByIdPort getScheduleByIdPort,
        UpdateSchedulePort upadateSchedulePort,
        DeleteSchedulePort deleteSchedulePort,
        GetUserByIdPort getUserByIdPort,
        GetServiceByIdPort getServiceByIdPort
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleRespose post(@RequestBody @Valid ScheduleRequest request) {
        var service = getServiceBySchedule(request);
        var user = getUserBySchedule(request);
        var schedule = request.toSchedule();
        var scheduleSaved = createSchedulePort.apply(schedule);
        return new ScheduleRespose().fromSchedule(scheduleSaved, service, user);
    }

    @GetMapping("user/{id}")
    public List<ScheduleRespose> getScheduleByUser(@PathVariable ObjectId id) {
        List<Schedule> schedules = getScheduleByUserPort.aplly(id);
        return schedules.stream()
                .map(schedule -> new ScheduleRespose()
                        .fromSchedule(schedule, null , null))
                .collect(Collectors.toList());

    }

    @GetMapping("{id}")
    public ScheduleRespose getSchedule(@PathVariable String id){
        Schedule schedule = getScheduleByIdPort.apply(id);
        return new ScheduleRespose().fromSchedule(schedule, null, null);
    }

    @PutMapping("{id}")
    public ScheduleRespose updateSchedule(@PathVariable String id, @RequestBody @Valid ScheduleRequest request) {
        var schedule = request.toSchedule();
        return new ScheduleRespose().fromSchedule(upadateSchedulePort.apply(id,schedule), null, null);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable String id) {
        deleteSchedulePort.apply(id);
    }


    private ServiceResponse getServiceBySchedule(ScheduleRequest request) {
       return  new ServiceResponse().fromService(getServiceByIdPort.apply(request.getServiceId().toString()));
    }

    private UserResponse getUserBySchedule(ScheduleRequest request) {
        return new UserResponse().fromUser(getUserByIdPort.apply(request.getUserId().toString()));
    }

}
