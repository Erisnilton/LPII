package br.com.projeto.lp2.core.domain;

import br.com.projeto.lp2.core.domain.enumaration.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private  String id;
    private ServiceType serviceType;
    private BigDecimal price;
    private BigDecimal duration;

}
