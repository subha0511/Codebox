package com.codebox.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {

  @Bean
  public RouterFunction<ServerResponse> problemServiceRoute() {
    return GatewayRouterFunctions.route("problem-service")
        .route(RequestPredicates.path("/api/product/**"),
            HandlerFunctions.http("http://localhost:8080")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> problemServiceSwaggerRoute() {
    return GatewayRouterFunctions.route("problem-service-swagger")
        .route(RequestPredicates.path("/aggregate/problem-service/v3/api-docs"),
            HandlerFunctions.http("http://localhost:8080")).filter(setPath("/api-docs")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> contestServiceRoute() {
    return GatewayRouterFunctions.route("contest-service")
        .route(RequestPredicates.path("/api/contest/**"),
            HandlerFunctions.http("http://localhost:8081")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> contestServiceSwaggerRoute() {
    return GatewayRouterFunctions.route("contest-service-swagger")
        .route(RequestPredicates.path("/aggregate/contest-service/v3/api-docs"),
            HandlerFunctions.http("http://localhost:8081")).filter(setPath("/api-docs")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> submissionServiceRoute() {
    return GatewayRouterFunctions.route("submission-service")
        .route(RequestPredicates.path("/api/submission/**"),
            HandlerFunctions.http("http://localhost:8082")).build();
  }

  @Bean
  public RouterFunction<ServerResponse> submissionServiceSwaggerRoute() {
    return GatewayRouterFunctions.route("submission-service-swagger")
        .route(RequestPredicates.path("/aggregate/submission-service/v3/api-docs"),
            HandlerFunctions.http("http://localhost:8082")).filter(setPath("/api-docs")).build();
  }

}
