package vadim.shtukan.API.Gateway.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SecurityController {
    @Value("${app.fusionauth.jwt-key-secret}")
    String secret;


    public DecodedJWT getJwtFromHttpRequest(ServerHttpRequest serverHttpRequest){
        DecodedJWT result = null;

        String token = null;

        try{
            List<String> paramValues = serverHttpRequest.getHeaders().get("Authorization");
            if (paramValues != null) {
                for (String value : paramValues) {
                    if(value.contains("Bearer ")){
                        token = value.replaceFirst("Bearer ", "");
                    }
                };
            }
            if(token == null){
                throw new NullPointerException("not JWT token!");
            }
//            if(!Objects.requireNonNull(serverHttpRequest.getHeaders().getFirst("Authorization")).contains("Bearer ")){
//                throw new NullPointerException("not JWT token!");
//            }
            //token = Objects.requireNonNull(serverHttpRequest.getHeaders().getFirst("Authorization")).replaceFirst("Bearer ", "");
        }catch (NullPointerException e){
            try {
                token = Objects.requireNonNull(serverHttpRequest.getCookies().getFirst("access_token")).getValue();
            }catch (NullPointerException ee){
                throw new NotFoundException("Token hot founded");
            }
        }

        //https://github.com/auth0/java-jwt
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
                    .build();
            result = verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new NotFoundException("JWT error");
        }

        return result;
    }
}
