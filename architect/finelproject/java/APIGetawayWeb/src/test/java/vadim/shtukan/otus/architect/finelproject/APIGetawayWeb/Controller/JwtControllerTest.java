package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models.User;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtControllerTest {
    @Autowired
    JwtController jwtController;

    private final String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VyR3JvdXBJZExpc3QiOlsiMCIsIjEiLCIyIl0sImNvbXBhbnlOYW1lIjoiU3dpZnQiLCJpc3MiOiJhdXRoMCIsImNvbXBhbnlFZ3JwbyI6Ijc0MCIsInVzZXJOYW1lIjoiSG93ZWxsIEthcmlhbmUiLCJ1c2VySWQiOiI1ZjJhNjhlNDkwMzg0OTBiYWUyNGU5YmQifQ.e2nL1OSfh30d3LO3KOZ5V_9NoyOA3JLBM3028PiWoXJj6BHtP4CuhKtt0t5tsl1p9lheu6e9NDaDHYMDp-k2q1Rq7GdRRH4qdNt1vXuDfwJVFUdRZ8YolDLZxndF3EHj-Srs1NJB3v3v8j0Sb59CVDzTl4hKHk-v6oNFNJuUD1w";
    @Test
    void getUserFromJwt() {
        User user = jwtController.getUserFromJwt(this.jwt);

        assertNotNull(user);
    }
}