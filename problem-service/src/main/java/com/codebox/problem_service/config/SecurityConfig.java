//package com.codebox.problem_service.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  @Autowired
//  private KeycloakConfigurationProperties keycloakConfig;
//
//  @Bean
//  JwtDecoder jwtDecoder() {
//    return NimbusJwtDecoder.withJwkSetUri(keycloakConfig.getCertificateUri())
//        .build();
//  }
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity.authorizeHttpRequests(c -> c.requestMatchers("/api/**")
//            .authenticated())
//        .cors(CorsConfigurer::disable)
//        .csrf(CsrfConfigurer::disable)
//        .oauth2ResourceServer(customizer -> customizer.jwt(jwt -> jwt.decoder(jwtDecoder())
//            .jwtAuthenticationConverter(jwtAuthenticationConverter())));
//    return httpSecurity.build();
//  }
//
//  @Bean
//  JwtAuthenticationConverter jwtAuthenticationConverter() {
//    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//    converter.setJwtGrantedAuthoritiesConverter(jwt -> {
//      Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims()
//          .get("realm_access");
//      List<String> roles = (List<String>) realmAccess.get("roles");
//      return roles.stream()
//          .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//          .collect(Collectors.toList());
//    });
//    return converter;
//  }
//
//}
