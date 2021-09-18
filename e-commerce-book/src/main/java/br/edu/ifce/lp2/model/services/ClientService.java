package br.edu.ifce.lp2.model.services;

import br.edu.ifce.lp2.model.entities.Client;
import br.edu.ifce.lp2.model.entities.Publisher;
import br.edu.ifce.lp2.model.repository.ClientRepository;
import br.edu.ifce.lp2.model.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client create(Client client) {

//        if (clientRepository.existsByName(publisher.getName())) {
//            throw new RuntimeException("Nome já existe");
//        }

        return clientRepository.save(client);
    }

    public Client update(String id, Client client) {

        var clientToUpdate = clientRepository.findByName(client.getName());

//        var p = clientRepository.findByName(publisher.getName());
//
//        if (p != null && !p.getId().equals(id)) {
//            throw new RuntimeException("Nome já existe");
//        }

        clientToUpdate.setName(client.getName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setPassword(client.getPassword());
        clientToUpdate.setEnabled(client.isEnabled());

        return clientRepository.save(clientToUpdate);

    }

    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client getById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher não existe!"));
    }

    public void delete(String id) {
        clientRepository.deleteById(id);
    }
}
