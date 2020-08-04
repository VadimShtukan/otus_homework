package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.Fusion.FusionAuthSerialisation;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.Fusion.FusionAuth_UserRegistration;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.UserRegistration;

@Configuration
public class GatewayRoutes {
    @Value("${app.services.key}")
    String keyHost;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("identity_registration_user", r -> r
                        .path("/identity/user")
                        .and().method(HttpMethod.POST)

                        .filters(f->f
                                        .rewritePath("/identity/user", "/user/registration")
//                                        .addRequestHeader("Authorization",this.fusionauthAuthorizationToken)
//                                        .modifyRequestBody(UserRegistration.class, FusionAuth_UserRegistration.class,  MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> {
//                                            return Mono.just(FusionAuthSerialisation.serialise(s, applicationId));
//                                        })
                                        .modifyResponseBody(String.class, String.class,(exchange, s) -> {
                                            //TODO send to Kafka event - add user -???
                                            return Mono.just(s);
                                        })
                        )
                        .uri(this.keyHost))

                .route("identity_login_user", r -> r
                        .path("/identity/user/login")
                        .and().method(HttpMethod.POST)
                        .filters(f->f
                                        .rewritePath("/identity/user/login", "/user/login")
                        )
                        .uri(this.keyHost))

                .build();
    }
}
