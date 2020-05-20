package stukan.vadim.otus.lesson2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukan.vadim.otus.lesson2.model.HealthModel;

@RestController
@RequestMapping("health")
public class MassageController {

    /**
     * Test service status
     * @return HealthModel
     */
    @GetMapping
    public HealthModel getHealth(){
        HealthModel healthModel = new HealthModel();
        healthModel.setStatusOk();

        return healthModel;
    }

}
