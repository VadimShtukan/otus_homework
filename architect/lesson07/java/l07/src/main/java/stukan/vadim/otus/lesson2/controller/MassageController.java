package stukan.vadim.otus.lesson2.controller;

import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukan.vadim.otus.lesson2.domain.User;
import stukan.vadim.otus.lesson2.model.AppProcessInfo;
import stukan.vadim.otus.lesson2.model.ApplicationVersion;
import stukan.vadim.otus.lesson2.model.HealthModel;
import stukan.vadim.otus.lesson2.repository.UserRepository;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Optional;

@RestController
public class MassageController {
    private static final Counter requestsVersion = Counter.build()
            .name("requests_version").help("Total requests for version.").register();

    private final ApplicationVersion applicationVersion;
    private final UserRepository userRepository;

    @Autowired
    public MassageController(ApplicationVersion applicationVersion,
                             UserRepository userRepository) {
        this.applicationVersion = applicationVersion;
        this.userRepository = userRepository;
    }


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
        requestsVersion.inc();

        return this.applicationVersion;
    }

    @GetMapping
    @RequestMapping("process")
    public AppProcessInfo getProcessInf(){
        AppProcessInfo appProcessInfo = new AppProcessInfo();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        appProcessInfo.setName(runtimeMXBean.getName().split("@")[1]);
        appProcessInfo.setId(Long.parseLong(runtimeMXBean.getName().split("@")[0]));

        return appProcessInfo;
    }

    @GetMapping
    @RequestMapping("testUser")
    public Optional<User> testUser(){
        User testUser = new User();

        testUser.setEmail("test@email.com");
        testUser.setFirstName("Test First Name");
        testUser.setLastName("Test Last Name");
        testUser.setPhone("12345");
        testUser.setUsername("Test User Name");

        testUser = userRepository.save(testUser);

        return userRepository.findById(testUser.getId());
    }

}
