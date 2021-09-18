package br.edu.ifce.lp2.controller.request;

import br.edu.ifce.lp2.model.entities.Publisher;
import lombok.Setter;

@Setter
public class PublisherRequest {

    private String name;
    private String phone;

    public Publisher toPublisher() {

        var publisher = new Publisher();

        publisher.setName(name);
        publisher.setPhone(phone);

        return publisher;

    }

}
