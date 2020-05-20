package vadim.shtukan.API.Gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RegistrationPreFilter extends AbstractGatewayFilterFactory<RegistrationPreFilter.Config> {
    public RegistrationPreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("inside SCGWPreFilter.apply method");

        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest().mutate().header(  "scgw-pre-header", Math.random()*10+"").build();
            ServerHttpRequest request = exchange.getRequest().mutate().header(  "Authorization", "vNt-OV_bElGvUYbMMm6Xqgmh3JvT9ivh0o4l0f1ugpM").build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
