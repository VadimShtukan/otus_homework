package vadim.shtukan.otus.architect.finelproject.Controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Domain.RequestIdempotency;
import vadim.shtukan.otus.architect.finelproject.Repository.RequestIdempotencyRepository;

import java.util.UUID;

@Controller
public class RequestIdempotencyController {
    @Autowired
    RequestIdempotencyRepository requestIdempotencyRepository;

    public boolean insertRequestId(UUID requestId){
        try {
            requestIdempotencyRepository.insert(new RequestIdempotency(requestId));
        }catch (DuplicateKeyException e){
            return false;
        }
        return true;
    }
}
