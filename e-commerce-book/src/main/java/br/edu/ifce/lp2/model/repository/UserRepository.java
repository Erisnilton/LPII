package br.edu.ifce.lp2.model.repository;

import br.edu.ifce.lp2.model.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
