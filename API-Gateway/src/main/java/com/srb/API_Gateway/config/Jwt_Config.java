package com.srb.API_Gateway.config;

import com.srb.API_Gateway.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class Jwt_Config {

//    private final JwtFilter jwtFilter;
//
//    public Jwt_Config(JwtFilter jwtFilter)
//    {
//        this.jwtFilter=jwtFilter;
//    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(auth-> auth
                        .pathMatchers("/auth/**")
                        .permitAll()
                        .anyExchange().permitAll())
               // .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());
        System.out.println("Enterend 1");
        return http.build();
    }

}
