package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.Base64;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models.User;

@Controller
public class JwtController {
    @Value("${app.jwt.key.public}")
    private String publicKeyString;

    public User getUserFromJwt(ServerHttpRequest serverHttpRequest) throws JWTVerificationException{
        String jwt = null;

        try{
            List<String> paramValues = serverHttpRequest.getHeaders().get("Authorization");
            if (paramValues != null) {
                for (String value : paramValues) {
                    if(value.contains("Bearer ")){
                        jwt = value.replaceFirst("Bearer ", "");
                    }
                };
            }
            if(jwt == null){
                throw new JWTVerificationException("JWT token empty!");
            }
       }catch (NullPointerException e){
            try {
                jwt = Objects.requireNonNull(serverHttpRequest.getCookies().getFirst("access_token")).getValue();
            }catch (NullPointerException ee){
                throw new JWTVerificationException("Token hot founded");
            }
        }
        return getUserFromJwt(jwt);
    }

    public User getUserFromJwt(String jwt) throws JWTVerificationException{
        User user;

        try {
            DecodedJWT decodedJWT = this.verifyJwt(jwt);
            user = new User(decodedJWT);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new JWTVerificationException("InvalidKeySpecException: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new JWTVerificationException("NoSuchAlgorithmException: " + e.getMessage());
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new JWTVerificationException("IllegalArgumentException: " + e.getMessage());
        }

        return user;
    }

    public DecodedJWT verifyJwt(String jwt) throws InvalidKeySpecException, NoSuchAlgorithmException, JWTVerificationException {
        KeyPair keyPair = this.getKeyPair();

        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        return verifier.verify(jwt);
    }

    private KeyPair getKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        PrivateKey privateKey;
        PublicKey publicKey;

        KeyFactory keyFactory;
        keyFactory = KeyFactory.getInstance("RSA");

        //byte[] encodedPv = Base64.decodeBase64(privateKeyString);
        //PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
        //privateKey = keyFactory.generatePrivate(keySpecPv);

        byte[] encodedPb = Base64.decodeBase64(publicKeyString);
        X509EncodedKeySpec keySpecPb = new X509EncodedKeySpec(encodedPb);
        publicKey = keyFactory.generatePublic(keySpecPb);

        return new KeyPair(publicKey, null);
    }
}
