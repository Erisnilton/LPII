package br.com.projeto.lp2.controller.schedule.response;

import br.com.projeto.lp2.controller.service.response.ServiceResponse;
import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.domain.Service;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class ScheduleRespose {

    private String id;
    private Service service;
    private LocalDateTime date;
    private LocalTime hours;
    private ObjectId userId;

    public ScheduleRespose fromSchedule(Schedule schedule) {
        this.id = schedule.getId();
        this.service = schedule.getService();
        this.date = schedule.getDate();
        this.hours = schedule.getHours();
        this.userId = schedule.getUserId();
        return this;
    }
}
