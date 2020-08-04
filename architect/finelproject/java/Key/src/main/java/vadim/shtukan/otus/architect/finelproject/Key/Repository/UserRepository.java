package vadim.shtukan.otus.architect.finelproject.Key.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import vadim.shtukan.otus.architect.finelproject.Key.Model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'serialNumber' : ?0 }")
    List<User> findBySerialNumber(String serialNumber);
}
