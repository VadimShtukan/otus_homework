package stukan.vadim.otus.lesson2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.InfoProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukan.vadim.otus.lesson2.model.AppProcessInfo;
import stukan.vadim.otus.lesson2.model.ApplicationVersion;
import stukan.vadim.otus.lesson2.model.HealthModel;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@RestController
//@RequestMapping("health")
public class MassageController {
    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private ApplicationVersion applicationVersion;

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

}
