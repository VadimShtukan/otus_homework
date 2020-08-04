package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EuSignatureTest {

    @Autowired
    private EuSignature euSignature;

    @Test
    void verifySignature() {
        String serialNumber = euSignature.verifySignature("NmFlNzU0ZDgtZDU3My0xMWVhLTg3ZDAtMDI0MmFjMTMwMDAz");

        assertEquals(serialNumber, "6ae754d8-d573-11ea-87d0-0242ac130003");
    }
}