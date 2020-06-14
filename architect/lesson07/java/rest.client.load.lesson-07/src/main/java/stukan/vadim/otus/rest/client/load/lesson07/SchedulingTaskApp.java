package stukan.vadim.otus.rest.client.load.lesson07;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stukan.vadim.otus.rest.client.load.lesson07.controller.UserController;
import stukan.vadim.otus.rest.client.load.lesson07.domain.User;

@Component
public class SchedulingTaskApp {
    UserController userController;
    private static final Logger log = LoggerFactory.getLogger(SchedulingTaskApp.class);

    @Autowired
    public SchedulingTaskApp(UserController userController) {
        this.userController = userController;
    }


    @Scheduled(fixedRateString = "${app.scan.rate}")
    public void sendRequest(){
        log.info("Start");

        User newUser = new User();
        newUser.setEmail("auto_email@email.com");
        newUser.setFirstName("Auto Name");
        newUser.setLastName("Auto Last Name");
        newUser.setPhone("+38055666570");
        newUser.setUsername("Auto Username");

        User cratedUser = userController.crete(newUser);
        log.info("Created user:" + cratedUser.getId());

        User returnedUser = userController.get(cratedUser.getId());
        log.info("Get usr:" + returnedUser);

        User changedUser = new User();
        changedUser.setEmail("changed_auto_email@email.com");
        changedUser.setFirstName("Changed Auto Name");
        changedUser.setLastName("Changed Auto Last Name");
        changedUser.setPhone("+77777777");
        changedUser.setUsername("Changed Auto Username");

        returnedUser = userController.change(returnedUser.getId(), changedUser);
        log.info("Changed User:" + returnedUser);

        userController.delete(returnedUser.getId());
        log.info("User deleted: " + returnedUser.getId());
    }
}
