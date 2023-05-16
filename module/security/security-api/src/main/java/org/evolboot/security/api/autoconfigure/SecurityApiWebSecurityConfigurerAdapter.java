package org.evolboot.security.api.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.api.SecurityAccessTokenAppService;
import org.evolboot.security.api.filter.AccessTokenAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author evol
 */
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final SecurityAccessTokenAppService securityAccessTokenAppService;
    private final SecurityDefaultConfigProperties securityDefaultConfigProperties;

    public SecurityApiWebSecurityConfigurerAdapter(SecurityAccessTokenAppService securityAccessTokenAppService, SecurityDefaultConfigProperties securityDefaultConfigProperties) {
        this.securityAccessTokenAppService = securityAccessTokenAppService;
        this.securityDefaultConfigProperties = securityDefaultConfigProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .disable()
                .addFilterAfter(new AccessTokenAuthenticationFilter(securityAccessTokenAppService, securityDefaultConfigProperties), BasicAuthenticationFilter.class)
                .csrf()
                .disable();
    }
}
