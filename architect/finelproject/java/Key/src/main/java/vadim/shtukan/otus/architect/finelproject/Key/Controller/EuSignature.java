package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import org.springframework.stereotype.Controller;

import java.util.Base64;

@Controller
public class EuSignature {

    public String verifySignature(String signature){
        //TODO verify by IIT
        //TODO verify time signature должна быть небольшая разница с текущим временем.

        byte[] signatureByte = Base64.getUrlDecoder().decode(signature);

        return new String(signatureByte);
    }
}
