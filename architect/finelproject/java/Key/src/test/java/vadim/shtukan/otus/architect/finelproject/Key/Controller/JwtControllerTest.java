package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vadim.shtukan.otus.architect.finelproject.Key.Model.Company;
import vadim.shtukan.otus.architect.finelproject.Key.Model.PayloadJwt;
import vadim.shtukan.otus.architect.finelproject.Key.Model.UserGroup;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtControllerTest {
    @Autowired
    private JwtController jwtController;

    @Test
    void GenerateKye() throws NoSuchAlgorithmException {
        //JwtController jwtController = new JwtController();

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(1024);

        KeyPair kp = keyGenerator.genKeyPair();
        PublicKey publicKey = (PublicKey) kp.getPublic();
        PrivateKey privateKey = (PrivateKey) kp.getPrivate();

        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("Public Key:");
        System.out.println(jwtController.convertToPublicKey(encodedPublicKey));
        System.out.println("Privat Key:");
        System.out.println(jwtController.convertToPrivateKey(encodedPrivateKey));
    }

    @Test
    void generateJwt() {
    }

    @Test
    void testGenerateJwt() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PayloadJwt payloadJwt = new PayloadJwt();
        payloadJwt.setUserName("TestName");
        payloadJwt.setCompany(new Company("22222", "12345644", "CompanyName"));
        payloadJwt.setUserId("1111");
        UserGroup userGroup1 = new UserGroup();
        userGroup1.setId("12");
        userGroup1.setName("CompanyUser");

        UserGroup userGroup2 = new UserGroup();
        userGroup2.setId("13");
        userGroup2.setName("CompanyAccouter");

        ArrayList<UserGroup> userGroupArrayList = new ArrayList<UserGroup>();
        userGroupArrayList.add(userGroup1);
        userGroupArrayList.add(userGroup2);

        payloadJwt.setUserGroupList(userGroupArrayList);


        String jwt = jwtController.generateJwt(payloadJwt);

        jwtController.verifyJwt(jwt);
    }
}