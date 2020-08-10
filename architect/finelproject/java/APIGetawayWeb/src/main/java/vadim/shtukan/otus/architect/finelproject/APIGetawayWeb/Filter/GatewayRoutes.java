package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER;


@Configuration
public class GatewayRoutes {
    @Value("${app.services.key}")
    private String keyHost;

    @Value("${app.services.document}")
    private String ettnHost;

    @Value("${app.services.billing}")
    private String billingHost;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, CheckJwtGatewayFilter checkJwtGatewayFilter) {
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

                .route("ettn", r -> r
                        .path("/ettn/**")
                        .filters(f->f
                                .filter(checkJwtGatewayFilter, ROUTE_TO_URL_FILTER_ORDER + 1)
                                //.addRequestHeader()
                                //.removeRequestHeader()
                        )
                        .uri(this.ettnHost))
                .route("billing", r -> r
                        .path("/billing/**")
                        .filters(f->f
                                        .filter(checkJwtGatewayFilter, ROUTE_TO_URL_FILTER_ORDER + 1)
                                //.addRequestHeader()
                                //.removeRequestHeader()
                        )
                        .uri(this.billingHost))
                .build();
    }
}
