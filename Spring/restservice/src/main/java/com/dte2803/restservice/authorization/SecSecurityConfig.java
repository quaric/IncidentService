package com.dte2803.restservice.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The security config class.
 * Users do not require password in current config, just username
 * configured usernames are: "mny066","obo015", "psu015"
 * Browser saves cookie as to confirm auth, or you can send username in authorization header
 */
@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mny066").password("{noop}").roles("USER")
                .and()
                .withUser("obo015").password("{noop}").roles("USER")
                .and()
                .withUser("psu005").password("{noop}").roles("USER");
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
