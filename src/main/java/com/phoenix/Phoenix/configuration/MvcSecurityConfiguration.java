package com.phoenix.Phoenix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((request) -> request
                .requestMatchers("/resources/**", "/account/**").permitAll()
                .requestMatchers("/landing/pageAdmin").hasAnyAuthority("Administrator", "Guest")
                .requestMatchers("/administrators/**", "/inventories/**", "/reservationLog/index", "/rooms/index", "/rooms/upsertForm",
                        "/rooms/insert", "/rooms/update", "/rooms/detail", "/rooms/deleteRoomInventories",
                        "/roomServices/**").hasAuthority("Administrator")
                .requestMatchers("/rooms/booking", "/rooms/detailRoom", "/rooms/reservationRoom", "/rooms/reservation").hasAuthority("Guest")
                .anyRequest().authenticated()
        ).formLogin((form) -> form
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/authenticating")
                .failureUrl("/account/failedLogin")
        ).rememberMe((context) -> context
                .key("liberate-tutumn-ex-inferis-ad-astra-per-aspera")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(86400)
        ).logout((logout) -> logout
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "remember-me-cookie").permitAll()
        ).exceptionHandling((exception) -> exception
                .accessDeniedPage("/account/accessDenied")
        );
        return httpSecurity.build();
    }

}
