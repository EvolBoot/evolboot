package org.evolboot.security.api.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.security.api.filter.AccessTokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author evol
 */
@EnableWebSecurity
@Configuration
@Slf4j
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityApiWebSecurityConfigurerAdapter {

    private final SecurityAccessTokenAppService securityAccessTokenAppService;
    private final SecurityDefaultConfigProperties securityDefaultConfigProperties;

    public SecurityApiWebSecurityConfigurerAdapter(SecurityAccessTokenAppService securityAccessTokenAppService, SecurityDefaultConfigProperties securityDefaultConfigProperties) {
        this.securityAccessTokenAppService = securityAccessTokenAppService;
        this.securityDefaultConfigProperties = securityDefaultConfigProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests(configurer ->
                        configurer.anyRequest().permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .addFilterAfter(new AccessTokenAuthenticationFilter(securityAccessTokenAppService, securityDefaultConfigProperties), BasicAuthenticationFilter.class)
                .build();
    }


}
