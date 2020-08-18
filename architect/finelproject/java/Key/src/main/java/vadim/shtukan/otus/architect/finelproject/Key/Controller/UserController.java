package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import io.prometheus.client.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.KafkaModels.UserKafka;
import vadim.shtukan.otus.architect.finelproject.Key.Models.*;
import vadim.shtukan.otus.architect.finelproject.Key.Repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private static final Histogram userControllerLatency = Histogram
            .build()
            .buckets(0.01, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 1, 2)
            .labelNames("UserController")
            .name("key_user_controller_latency")
            .help("Время, которое затрачивается на операции с пользователями.")
            .register();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtController jwtController;
    
    @Autowired
    private EuSignature euSignature;

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public UserController() {
    }

    public UserRegistration registration(UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Histogram.Timer requestTimer_createUserLatency;
        requestTimer_createUserLatency = userControllerLatency.labels("registration").startTimer();

        //todo validate userRegistration
        //todo check if user has been registrated
        //todo add clear group
        //todo add user created date
        //todo Компанию в отдельный объект
        //todo добавить ID компании в jwt

        UserGroup userGroup = new UserGroup("1");
        ArrayList<UserGroup> userGroupList = new ArrayList<>();
        userGroupList.add(userGroup);
        userRegistration.setUserGroupList(userGroupList);


        userRegistration.setSerialNumber(euSignature.verifySignature(userRegistration.getSignature()));

        UserRegistration user = userRepository.save(userRegistration);

        kafkaTemplate.send("user.new", new UserKafka(user.getId()));

        requestTimer_createUserLatency.observeDuration();

        return user;
    }

    private UserLogin getNewJwtForUserByUserId(String id) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not founded. Id: " + id));

        return this.getNewJwtForUser(user);
    }

    private UserLogin getNewJwtForUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PayloadJwt payloadJwt = user.toPayloadJwt();

        String jwt = jwtController.generateJwt(payloadJwt);

        return new UserLogin(payloadJwt.getUserId(), jwt);
    }


    public UserLogin login(UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Histogram.Timer requestTimer_loginUserLatency;
        requestTimer_loginUserLatency = userControllerLatency.labels("login").startTimer();

        String serialNumber = euSignature.verifySignature(userRegistration.getSignature());

        List<User> userLoginningList = userRepository.findBySerialNumber(serialNumber);

        UserLogin userLogin = this.getNewJwtForUser(userLoginningList.get(userLoginningList.size() - 1));

        requestTimer_loginUserLatency.observeDuration();

        return userLogin;
    }
}
