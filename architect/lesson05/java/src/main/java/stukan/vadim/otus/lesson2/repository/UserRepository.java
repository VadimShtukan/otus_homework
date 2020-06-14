package stukan.vadim.otus.lesson2.repository;

import org.springframework.data.repository.CrudRepository;
import stukan.vadim.otus.lesson2.domain.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
