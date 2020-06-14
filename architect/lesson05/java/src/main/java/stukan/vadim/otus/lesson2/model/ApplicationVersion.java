package stukan.vadim.otus.lesson2.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("applicationVersion")
public class ApplicationVersion {
    @Value("${app.version}")
    public String appVersion;

    @Value("${spring.datasource.url}")
    public String dataSourceUrl;

    @Value("${spring.datasource.username}")
    public String dataSourceUsername;

    @Value("${spring.datasource.password}")
    public String dataSourcePassword;


}
