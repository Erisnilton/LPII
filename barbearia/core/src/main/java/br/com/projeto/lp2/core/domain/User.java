package br.com.projeto.lp2.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String password;
}
