package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vadim.shtukan.otus.architect.finelproject.Key.Model.Company;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserLogin;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserRegistration;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {
    @Autowired
    UserController userController;

    private final String signature = "NmFlNzU0ZDgtZDU3My0xMWVhLTg3ZDAtMDI0MmFjMTMwMDAz";
    private final String serialNumber = "6ae754d8-d573-11ea-87d0-0242ac130003";

    @Test
    void registration() throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setSignature(signature);
        userRegistration.setCompany(new Company("1234", "655474789", "Company Name"));
        userRegistration.setEmail("test@test.com");
        userRegistration.setFirstName("Name");
        userRegistration.setLastName("Last name");
        userRegistration.setMobilePhone("343-23-34");

        UserLogin userLogin = userController.registration(userRegistration);

        assertNotNull(userLogin.getUserId());
        assertEquals(userRegistration.getSerialNumber(), serialNumber);
        assertNotNull(userLogin.getJwt());
    }

    @Test
    void login() throws Exception{
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setSignature(signature);

        UserLogin userLogin = userController.login(userRegistration);

        assertNotNull(userLogin.getJwt());
    }
}