package vadim.shtukan.otus.architect.finelproject.Domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("applicationVersion")
public class ApplicationVersion {
    @Value("${app.version}")
    public String appVersion;
}
