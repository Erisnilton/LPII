package br.com.projeto.lp2.controller.service.request;

import br.com.projeto.lp2.core.domain.Service;
import br.com.projeto.lp2.core.domain.User;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class ServiceRequest {
    private String name;
    private BigDecimal price;
    private BigDecimal duration;


    public Service toService() {
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        service.setDuration(duration);
        return  service;
    }
}
