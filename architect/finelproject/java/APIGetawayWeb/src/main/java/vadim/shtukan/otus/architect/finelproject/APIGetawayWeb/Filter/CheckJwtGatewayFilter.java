package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Controller.JwtController;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models.User;

@Component
public class CheckJwtGatewayFilter implements GatewayFilter {
    @Autowired
    JwtController jwtController;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        User user;

        try {
            user = this.jwtController.getUserFromJwt(exchange.getRequest());
            ObjectMapper objectMapper = new ObjectMapper();
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-Auth-User", objectMapper.writeValueAsString(user))
                    .build();
            exchange.mutate().request(request).build();

            //todo service JWT. Добавить дополнительный токен для того, что бы сервис-приемщик убедился в том, что к нему обращается авторизованный сервис!
        }
        catch (JWTVerificationException e){
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return Mono.empty();
        }
        return chain.filter(exchange);
    }
}
