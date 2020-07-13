package vadim.shtukan.otus.architect.finelproject.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vadim.shtukan.otus.architect.finelproject.Domain.RequestIdempotency;

import java.util.UUID;

public interface RequestIdempotencyRepository  extends MongoRepository<RequestIdempotency, UUID> {
}
