package vadim.shtukan.otus.architect.finelproject.ControllerRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vadim.shtukan.otus.architect.finelproject.Domain.ApplicationVersion;
import vadim.shtukan.otus.architect.finelproject.Domain.HealthModel;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@RestController
//@RequestMapping("health")
public class MassageController {
    @Value("${app.version}")
    private String appVersion;


    /**
     * Test service status
     * @return HealthModel
     */
    @GetMapping
    @RequestMapping("health")
    public HealthModel getHealth(){
        HealthModel healthModel = new HealthModel();
        healthModel.setStatusOk();

        return healthModel;
    }

    @GetMapping
    @RequestMapping("version")
    public ApplicationVersion getAppVersion(){
        return new ApplicationVersion();
    }


}
