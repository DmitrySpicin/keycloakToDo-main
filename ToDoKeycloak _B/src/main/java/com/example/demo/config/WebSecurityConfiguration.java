package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().and()
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/create").permitAll()
                .anyRequest().authenticated().and()
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.build();
    }

    private static JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("resource_access.user.roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
