package br.edu.ifce.lp2.controller.response;

import br.edu.ifce.lp2.model.entities.Client;
import lombok.Getter;

@Getter
public class ClientResponse {
    private String id;
    private String name;
    private String email;
    private boolean enabled;

    public ClientResponse fromClient(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.enabled = client.isEnabled();
        return this;
    }
}
