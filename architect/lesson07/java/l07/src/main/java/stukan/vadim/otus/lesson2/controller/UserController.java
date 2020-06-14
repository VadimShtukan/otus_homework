package stukan.vadim.otus.lesson2.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import stukan.vadim.otus.lesson2.domain.User;
import stukan.vadim.otus.lesson2.exception.UserCreateException;
import stukan.vadim.otus.lesson2.exception.UserNotFoundedException;
import stukan.vadim.otus.lesson2.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    static final Histogram requestLatencyUser = Histogram.build()
            .buckets(.005, .01, .025, .05, .075, .1, .25, .5, .75, 1, 2.5, 5, 7.5, 10)
            .labelNames("method", "endpoint")
            .name("otus_sa_requests_latency").help("Request latency for Otus App.").register();

//
//    private static final Counter requestsUserGet = Counter.build()
//            .name("otus_sa_user_get_requests").help("Total requests for /user GET.").register();
//    static final Histogram requestLatencyUserGet = Histogram.build()
//            .buckets(.5, .95, .99)
//            .name("otus_sa_user_get_requests_latency").help("Request latency for /user GET in seconds.").register();
//
//    private static final Counter requestsUserAdd = Counter.build()
//            .name("otus_sa_user_add_requests").help("Total requests for /user POST.").register();
//
//    ///////////
//    static final Histogram requestLatencyUserAdd = Histogram.build()
//            .buckets(.5, .95, .99)
//            .labelNames("method", "endpoint")
//            .name("otus_sa_user_add_requests_latency").help("Request latency for /user POST in seconds.").register();
//
//    private static final Counter requestsUserDelete = Counter.build()
//            .name("otus_sa_user_delete_requests").help("Total requests for /user DELETE.").register();
//    static final Histogram requestLatencyUserDelete = Histogram.build()
//            .buckets(.5, .95, .99)
//            .name("otus_sa_user_delete_requests_latency").help("Request latency for /user DELETE in seconds.").register();
//
//    private static final Counter requestsUserChange = Counter.build()
//            .name("otus_sa_user_change_requests").help("Total requests for /user PUT.").register();
//
//    private static final Histogram requestLatencyUserGetAll = Histogram.build()
//            .buckets(.5, .95, .99)
//            .name("otus_sa_user_get_all_requests").help("Total requests for / (all) get.").register();
//
//    static final Histogram requestLatencyUserChange = Histogram.build()
//            .buckets(.5, .95, .99)
//            .name("otus_sa_user_change_requests_latency").help("Request latency for /user PUT in seconds.").register();

    static final Gauge summaryRate = Gauge.build()
            .name("otus_sa_requests_test_gauge_rate").help("Test gauge metric for rate.").register();

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/rate/{rate}")
    public Integer getUser(@PathVariable Integer rate) {
        summaryRate.set(rate);
        return rate;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id, HttpServletRequest httpRequest) {
        //requestsUserGet.inc();
        String d = httpRequest.getServerName();
        String f = httpRequest.getLocalName();
        String c = httpRequest.getContentType();
        return requestLatencyUser.labels("GET", "/user/{id}").time(() ->{
                return userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundedException(id.toString()));
        });
    }

    @GetMapping()
    public Iterable<User> getUser() {
        //requestsUserGet.inc();
        Histogram.Timer requestTimer = requestLatencyUser.labels("GET", "/user").startTimer();
        Iterable<User> result;

        try{
            result = userRepository.findAll();
        }finally {
            requestTimer.observeDuration();
        }

        return result;
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        //requestsUserAdd.inc();
        Histogram.Timer requestTimer = requestLatencyUser.labels("POST", "/user").startTimer();
        User userCreated;

        try {
            User finalUser = user;

            if (user.getUsername() == null || user.getUsername().equals(""))
                throw new UserCreateException("User name can not be null");

            try {
                user = userRepository.save(user);
            } catch (Exception e) {
                throw new UserCreateException("Can not create user: " + finalUser.getUsername());
            }

            userCreated = userRepository.findById(user.getId())
                    .orElseThrow(() -> new UserCreateException("Can not create user: " + finalUser.getUsername()));
        }finally {
            requestTimer.observeDuration();
        }
        return userCreated;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        //requestsUserDelete.inc();

        Histogram.Timer requestTimer = requestLatencyUser.labels("DELETE", "/user/{id}").startTimer();
        User userCreated;
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundedException(id.toString());
        }finally {
            requestTimer.observeDuration();
        }
    }

    @PutMapping("/{id}")
    public Optional<User> changeUser(@RequestBody User user, @PathVariable UUID id){
        //requestsUserChange.inc();
        Histogram.Timer requestTimer = requestLatencyUser.labels("PUT", "/user/{id}").startTimer();
        Optional<User> userChanged;

        try {
            userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundedException(id.toString()));

            if (user.getUsername() == null || user.getUsername().equals(""))
                throw new UserCreateException("User name can not be null");

            user.setId(id);
            user = userRepository.save(user);

            userChanged = userRepository.findById(user.getId());
        }finally {
            requestTimer.observeDuration();
        }
        return userChanged;
    }

}
