package com.codebox.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

  @Bean
  public RouterFunction<ServerResponse> problemServiceRoute() {
    return GatewayRouterFunctions.route("problem-service")
        .route(RequestPredicates.path("/api/product/**"),
            HandlerFunctions.http("http://localhost:8080")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> contestServiceRoute() {
    return GatewayRouterFunctions.route("contest-service")
        .route(RequestPredicates.path("/api/contest/**"),
            HandlerFunctions.http("http://localhost:8081")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> submissionServiceRoute() {
    return GatewayRouterFunctions.route("submission-service")
        .route(RequestPredicates.path("/api/submission/**"),
            HandlerFunctions.http("http://localhost:8082")).build();
  }

}
