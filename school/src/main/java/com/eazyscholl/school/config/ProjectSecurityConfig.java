package com.eazyscholl.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(requests -> requests
                .requestMatchers(
                    "/", "/home", "/holidays/**", "/contact", "/saveMsg",
                    "/courses", "/about", "/login", "/register",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()
                .requestMatchers("/dashboard").authenticated()
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")              // GET /login -> LoginController
                .loginProcessingUrl("/login")     // POST /login handled by Spring Security
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
            );

        return http.build();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("1234").roles("USER")
            .and()
            .withUser("admin").password("54321").roles("USER", "ADMIN")
            .and()
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}