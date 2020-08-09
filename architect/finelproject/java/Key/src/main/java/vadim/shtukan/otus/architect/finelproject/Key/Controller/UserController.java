package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.UserKafka;
import vadim.shtukan.otus.architect.finelproject.Key.Model.PayloadJwt;
import vadim.shtukan.otus.architect.finelproject.Key.Model.User;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserLogin;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserRegistration;
import vadim.shtukan.otus.architect.finelproject.Key.Repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
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

    public UserLogin registration(UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //todo validate userRegistration
        //todo check if user has been registrated
        //todo add clear group
        //todo add user created date
        //todo Компанию в отдельный объект
        //todo добавить ID компании в jwt

        userRegistration.setSerialNumber(euSignature.verifySignature(userRegistration.getSignature()));

        User user = userRepository.save((User)userRegistration);

        kafkaTemplate.send("user.new", new UserKafka(user.getId()));

        return this.getNewJwtForUserByUserId(user.getId());
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
        String serialNumber = euSignature.verifySignature(userRegistration.getSignature());

        List<User> userLoginningList = userRepository.findBySerialNumber(serialNumber);

        return this.getNewJwtForUser(userLoginningList.get(userLoginningList.size() - 1));
    }
}
