package vadim.shtukan.API.Gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vadim.shtukan.API.Gateway.controller.SecurityController;

@Component
public class GetUserFilter implements GatewayFilter {
    @Value("${app.fusionauth.jwt-key-secret}")
    String secret;

    @Value("${app.fusionauth.authorization-token}")
    String fusionauthAuthorizationToken;

    final
    SecurityController securityController;

    public GetUserFilter(SecurityController securityController) {
        this.securityController = securityController;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        DecodedJWT jwt;

        try {
            jwt = this.securityController.getJwtFromHttpRequest(exchange.getRequest());
        }catch (NotFoundException ignored){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }

//        String userIdJwt = jwt.getSubject();
//        String userIdUrl = exchange.getRequest().getPath().value();
        //Security bug! Don`t use it in prod!
        if(!exchange.getRequest().getPath().value().contains(jwt.getSubject())){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }

        //exchange.getRequest().getHeaders().set("Authorization", this.fusionauthAuthorizationToken);

        return chain.filter(exchange);
    }


}
