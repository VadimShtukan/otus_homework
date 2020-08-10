package vadim.shtukan.otus.architect.finelproject.Billing.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

    public User getUser(HttpServletRequest httpRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(httpRequest.getHeader("X-Auth-User"), User.class);
    }
}
