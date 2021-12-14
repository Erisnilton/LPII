package br.com.projeto.lp2.controller.schedule.response;

import br.com.projeto.lp2.controller.service.response.ServiceResponse;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.domain.Service;
import br.com.projeto.lp2.core.domain.User;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class ScheduleRespose {

    private String id;
    private ObjectId serviceId;
    private LocalDateTime time;

    public ScheduleRespose fromSchedule(Schedule schedule) {
        this.id = schedule.getId();
        this.serviceId = schedule.getService();
        this.time = schedule.getTime();
        return this;
    }
}
