package com.codebox.api_gateway.routes;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

import java.net.URI;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> problemServiceRoute() {
        return GatewayRouterFunctions.route("problem-service")
                .route(RequestPredicates.path("/api/problem/**"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "problemServiceCircuitBreaker", URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> problemServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("problem-service-swagger")
                .route(
                        RequestPredicates.path("/aggregate/problem-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "problemServiceSwaggerCircuitBreaker", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> contestServiceRoute() {
        return GatewayRouterFunctions.route("contest-service")
                .route(RequestPredicates.path("/api/contest/**"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "contestServiceCircuitBreaker", URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> contestServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("contest-service-swagger")
                .route(
                        RequestPredicates.path("/aggregate/contest-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "contestServiceSwaggerCircuitBreaker", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> submissionServiceRoute() {
        return GatewayRouterFunctions.route("submission-service")
                .route(RequestPredicates.path("/api/submission/**"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "submissionServiceCircuitBreaker", URI.create("forward:/fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> submissionServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("submission-service-swagger")
                .route(
                        RequestPredicates.path("/aggregate/submission-service/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                        "submissionServiceSwaggerCircuitBreaker", URI.create("forward:/fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return GatewayRouterFunctions.route("fallback")
                .GET("forward:/fallback", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable, please try again later"))
                .build();
    }
}
