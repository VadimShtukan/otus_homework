package vadim.shtukan.otus.architect.finelproject.Domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Document(collection = "RequestIdempotency")
public class RequestIdempotency {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Indexed(unique = true)
    private UUID requestId;

    public RequestIdempotency(UUID requestId) {
        this.requestId = requestId;
    }
}
