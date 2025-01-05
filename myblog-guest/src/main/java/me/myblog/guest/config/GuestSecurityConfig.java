package me.myblog.guest.config;

import me.myblog.guest.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class GuestSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
            AuthenticationEntryPoint authenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler
    ) throws Exception {
        httpSecurity.csrf(CsrfConfigurer<HttpSecurity>::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(FormLoginConfigurer<HttpSecurity>::disable)
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/login").anonymous()
                                .requestMatchers("/logout").authenticated()
                                .requestMatchers("/user/userInfo").authenticated()
                                .requestMatchers("/upload").authenticated()
                                .anyRequest().permitAll()
                ).exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                ).logout(LogoutConfigurer<HttpSecurity>::disable)
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults());

        return httpSecurity.build();
    }
}