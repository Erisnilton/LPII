package br.com.projeto.lp2.controller.schedule.request;

import br.com.projeto.lp2.core.domain.Schedule;
import br.com.projeto.lp2.core.domain.Service;
import lombok.Getter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class ScheduleResquest {
    @NotBlank
    private Service service;
    @NotBlank
    private LocalDateTime date;
    @NotBlank
    private LocalTime hours;
    @NotBlank
    private ObjectId userId;

    public Schedule toSchedule() {
        Schedule schedule = new Schedule();
        schedule.setService(service);
        schedule.setDate(date);
        schedule.setHours(hours);
        schedule.setUserId(userId);
        return  schedule;
    }
}
