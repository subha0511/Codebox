// package com.codebox.api_gateway.config;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
// import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
// import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
//
//  @Autowired
//  private ClientRegistrationRepository clientRegistrationRepository;
//
//  @Bean
//  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity.authorizeHttpRequests(
//            c -> c
//                .requestMatchers("/api/problem/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated())
//        .cors(CorsConfigurer::disable)
//        .csrf(CsrfConfigurer::disable)
//        .oauth2Login(Customizer.withDefaults())
//        .logout(
//            lo -> lo
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .logoutSuccessHandler(oidcLogoutSuccessHandler()));
//    return httpSecurity.build();
//  }
//
//  private LogoutSuccessHandler oidcLogoutSuccessHandler() {
//    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
//        this.clientRegistrationRepository);
//    oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
//    return oidcLogoutSuccessHandler;
//  }
// }
