package stukan.vadim.otus.rest.client.load.lesson07.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import stukan.vadim.otus.rest.client.load.lesson07.domain.User;

import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.util.UUID;

@Controller
public class UserController {
    @Value("${app.scan.url}")
    private String scanUrl;

    HttpHeaders httpHeaders = new HttpHeaders();

    public UserController(@Value("${app.scan.host}") String host) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        httpHeaders.set("Host", host);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public User crete(User user){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> represent = restTemplate.exchange(
                this.scanUrl + "/user",
                HttpMethod.POST,
                new HttpEntity<>(user, this.httpHeaders),
                User.class
                );
//        ResponseEntity<User> represent = restTemplate.postForEntity(
//                this.scanUrl + "/user",
//                user,
//                User.class);
          return represent.getBody();
    }

    public User get(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> represent = restTemplate.exchange(
                this.scanUrl + "/user/" + id,
                HttpMethod.GET,
                new HttpEntity<>(null, this.httpHeaders),
                User.class
        );

//        ResponseEntity<User> represent = restTemplate.getForEntity(
//                this.scanUrl + "/user/" + id, User.class);
        return represent.getBody();
    }

    public User change(UUID id, User changedUser) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> represent = restTemplate.exchange(
                this.scanUrl + "/user/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(changedUser, this.httpHeaders),
                User.class
        );
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(
//                this.scanUrl + "/user/" + id, changedUser, User.class);

        return this.get(id);
    }

    public void delete(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                this.scanUrl + "/user/" + id,
                HttpMethod.DELETE,
                new HttpEntity<>(null, this.httpHeaders),
                User.class
        );
//        restTemplate.delete(
//                this.scanUrl + "/user/" + id, User.class);
    }
}
