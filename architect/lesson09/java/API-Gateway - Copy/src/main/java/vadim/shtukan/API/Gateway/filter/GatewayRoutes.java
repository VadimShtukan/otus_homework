package vadim.shtukan.API.Gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import vadim.shtukan.API.Gateway.controller.FusionAuthSerialisation;
import vadim.shtukan.API.Gateway.model.*;

import static org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER;

@Configuration
public class GatewayRoutes {
    @Value("${app.fusionauth.host}")
    String fusionauthHost;

    @Value("${app.fusionauth.authorization-token}")
    String fusionauthAuthorizationToken;

    @Value("${app.fusionauth.applicationId}")
    String applicationId;


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, GetUserFilter getUserFilter) {
        return builder.routes()
                .route("identity_registration_user", r -> r
                        .path("/identity/registration")
                        .and().method(HttpMethod.POST)
                        .filters(f->f
                                        .rewritePath("/identity/registration", "/api/user/registration")
                                        .addRequestHeader("Authorization",this.fusionauthAuthorizationToken)
                                        .modifyRequestBody(UserForRegistration.class, FusionAuth_RegistrationUser.class,  MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> {
                                            return Mono.just(FusionAuthSerialisation.serialisat(s, applicationId));
                                        })
//                                        .modifyResponseBody(String.class, String.class,(exchange, s) -> {
//                                            return Mono.just(s);
//                                        })

                        )
                        .uri(this.fusionauthHost))
                .route("identity_login_user", r -> r
                        .path("/identity/login")
                        .and().method(HttpMethod.POST)
                        .filters(f->f
                                .rewritePath("/identity/login", "/api/login")
                                .addRequestHeader("Authorization",this.fusionauthAuthorizationToken)

                                .modifyRequestBody(FusionAuth_Login.class, FusionAuth_Login.class,  MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> {

                                    return Mono.just(FusionAuthSerialisation.serialisat(s, applicationId));
                                })
//                                .modifyResponseBody(String.class, String.class,(exchange, s) -> {
//                                    return Mono.just(s);
//                                })

                        )
                        .uri(this.fusionauthHost))
                .route("identity_get_user", r -> r
                        .path("/identity/user/**")
                        .and().method(HttpMethod.GET)
                        .filters(f->f
                                .filter(getUserFilter, ROUTE_TO_URL_FILTER_ORDER + 1)
                                .rewritePath("/identity/user/(?<userId>.*)", "/api/user/${userId}")
                                .modifyResponseBody(FusionAuth_UserUser.class, User.class,(exchange, s) -> {
                                    return Mono.just(FusionAuthSerialisation.serialisat(s));
                                })
                        )
                        .uri(this.fusionauthHost))
                .route("identity_get_user", r -> r
                        .path("/identity/user/**")
                        .and().method(HttpMethod.PUT)
                        .filters(f->f
                                .filter(getUserFilter, ROUTE_TO_URL_FILTER_ORDER + 1)
                                .addRequestHeader("Authorization",this.fusionauthAuthorizationToken)
                                .rewritePath("/identity/user/(?<userId>.*)", "/api/user/${userId}")
                                .modifyRequestBody(User.class, FusionAuth_UserUser.class,  MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> {
                                    return Mono.just(FusionAuthSerialisation.serialisat(s));
                                })
//                                .modifyResponseBody(FusionAuth_UserUser.class, User.class,(exchange, s) -> {
//                                    return Mono.just(FusionAuthSerialisation.serialisat(s));
//                                })
                        )
                        .uri(this.fusionauthHost))
                .build();
    }
}
