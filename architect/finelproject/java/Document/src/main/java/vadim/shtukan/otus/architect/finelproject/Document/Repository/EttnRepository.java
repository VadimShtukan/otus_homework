package vadim.shtukan.otus.architect.finelproject.Document.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vadim.shtukan.otus.architect.finelproject.Document.Models.EttnXml;

public interface EttnRepository extends MongoRepository<EttnXml, String> {
}
