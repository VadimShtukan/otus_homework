package vadim.shtukan.otus.architect.finelproject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import vadim.shtukan.otus.architect.finelproject.Domain.Cart;

import java.util.UUID;

public interface CartRepository extends MongoRepository<Cart, UUID> {

    @Query("{lastRequestId : ?0}")
    public Cart findByLastRequestIdQuery(UUID lastRequestId);
}
