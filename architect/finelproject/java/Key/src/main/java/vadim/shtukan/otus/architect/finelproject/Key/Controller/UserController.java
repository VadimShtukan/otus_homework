package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Key.Model.PayloadJwt;
import vadim.shtukan.otus.architect.finelproject.Key.Model.User;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserLogin;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserRegistration;
import vadim.shtukan.otus.architect.finelproject.Key.Repository.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtController jwtController;

    public UserController() {
    }

    public UserLogin registration(UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //todo validate userRegistration
        //todo check if user has been registrated
        //todo check signature by EUSIGN
        //todo add clear group

        byte[] signatureByte = Base64.getUrlDecoder().decode(userRegistration.getSignature());

        userRegistration.setRegKeyId(new String(signatureByte));

        User user = userRepository.save((User)userRegistration);

        UserLogin userLogin = this.getNewJwtForUser(user.getId());

        return this.getNewJwtForUser(user.getId());
    }

    private UserLogin getNewJwtForUser(String id) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not founded. Id: " + id));

        PayloadJwt payloadJwt = user.toPayloadJwt();

        String jwt = jwtController.generateJwt(payloadJwt);

        return new UserLogin(payloadJwt.getUserId(), jwt);
    }


}
