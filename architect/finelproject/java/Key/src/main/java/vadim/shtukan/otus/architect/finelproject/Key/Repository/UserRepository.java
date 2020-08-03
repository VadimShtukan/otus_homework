package vadim.shtukan.otus.architect.finelproject.Key.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Key.Model.User;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserRegistration;

public interface UserRepository extends MongoRepository<User, String> {

}
