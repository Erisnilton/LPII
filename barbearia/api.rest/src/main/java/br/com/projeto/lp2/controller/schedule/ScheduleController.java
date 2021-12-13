package br.com.projeto.lp2.controller.schedule;

import br.com.projeto.lp2.controller.schedule.request.ScheduleResquest;
import br.com.projeto.lp2.controller.schedule.response.ScheduleRespose;
import br.com.projeto.lp2.core.ports.driver.schedule.CreateSchedulePort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("schedule")
public record ScheduleController(CreateSchedulePort createSchedulePort) {

    @PostMapping
    public ScheduleRespose post(@RequestBody @Valid ScheduleResquest resquest) {
        return  null;
    }

}
