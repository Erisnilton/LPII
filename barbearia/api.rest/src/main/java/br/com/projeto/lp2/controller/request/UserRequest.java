package br.com.projeto.lp2.controller.request;

import br.com.projeto.lp2.core.domain.Address;
import br.com.projeto.lp2.core.domain.User;
import lombok.Getter;

@Getter
public class UserRequest {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private String password;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(password);
        return  user;
    }
}
