package com.hospital.ERP.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth


                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/doctor/**").hasRole("DOCTOR")

                        .requestMatchers("/patient/**").hasRole("PATIENT")

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers("/superadmin/**").hasRole("SUPERADMIN")

                        .requestMatchers("/api/user/**").permitAll()

                        //.hasAnyRole("ADMIN", "DOCTOR","PATIENT")

                        .requestMatchers("/api/patients/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
