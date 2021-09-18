package br.edu.ifce.lp2.model.repository;

import br.edu.ifce.lp2.model.entities.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {

    boolean existsByName(String name);

    Publisher findByName(String name);

}
