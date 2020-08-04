package vadim.shtukan.otus.architect.finelproject.Key.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import vadim.shtukan.otus.architect.finelproject.Key.Model.*;
import org.springframework.stereotype.Controller;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;



//@Scope("prototype")
@Controller
public class JwtController {
    @Value("${app.jwt.key.public}")
    private String publicKeyString;
    
    @Value("${app.jwt.key.private}")
    private String privateKeyString;


    public DecodedJWT verifyJwt(String jwt) throws InvalidKeySpecException, NoSuchAlgorithmException, JWTVerificationException {
        KeyPair keyPair = this.getKeyPair();

        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), null);
        JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
        DecodedJWT decodedJWT = verifier.verify(jwt);

        return decodedJWT;
    }

    public String generateJwt(PayloadJwt payloadJwt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPair keyPair = this.getKeyPair();

        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId", payloadJwt.getUserId())
                .withClaim("userName", payloadJwt.getUserName())
                .withClaim("companyName", payloadJwt.getCompany().getName())
                .withClaim("companyEgrpo", payloadJwt.getCompany().getEgrpo())
                .withClaim("companyId", payloadJwt.getCompany().getId())
                .withArrayClaim("userGroupIdList", payloadJwt.getUserGroupIdList())
                .sign(algorithm);
        return token;
    }

    private KeyPair getKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey privateKey;
        PublicKey publicKey;

        KeyFactory keyFactory;
        keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedPv = Base64.decodeBase64(privateKeyString);

        PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
        privateKey = keyFactory.generatePrivate(keySpecPv);

        byte[] encodedPb = Base64.decodeBase64(publicKeyString);
        X509EncodedKeySpec keySpecPb = new X509EncodedKeySpec(encodedPb);
        publicKey = keyFactory.generatePublic(keySpecPb);

        return new KeyPair(publicKey, privateKey);
    }


    // Add BEGIN and END comments
    public String convertToPublicKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(key);
        result.append("\n-----END PUBLIC KEY-----");
        return result.toString();
    }

    public String convertToPrivateKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PRIVATE KEY-----\n");
        result.append(key);
        result.append("\n-----END PRIVATE KEY-----");
        return result.toString();
    }
}
