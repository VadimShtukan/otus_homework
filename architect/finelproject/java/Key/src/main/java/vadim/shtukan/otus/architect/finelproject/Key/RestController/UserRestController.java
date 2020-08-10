package vadim.shtukan.otus.architect.finelproject.Key.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vadim.shtukan.otus.architect.finelproject.Key.Controller.UserController;
import vadim.shtukan.otus.architect.finelproject.Key.Model.User;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserLogin;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserRegistration;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("user")
public class UserRestController {
    @Autowired
    private UserController userController;

    @PostMapping
    @RequestMapping("registration")
    public UserRegistration createUser(@RequestBody UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //TODO catch exceptions
        return userController.registration(userRegistration);
    }

    @PostMapping
    @RequestMapping("login")
    public UserLogin login(@RequestBody UserRegistration userRegistration) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //TODO catch exceptions

        return userController.login(userRegistration);
    }
}
