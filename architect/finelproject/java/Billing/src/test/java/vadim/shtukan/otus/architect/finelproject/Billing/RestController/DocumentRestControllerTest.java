package vadim.shtukan.otus.architect.finelproject.Billing.RestController;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DocumentRestControllerTest {
    @Autowired
    private DocumentRestController documentRestController;

    @Test
    void getEttnById() {
        documentRestController.getEttnById("5f3039c93c094d089e0b1b92");
    }
}