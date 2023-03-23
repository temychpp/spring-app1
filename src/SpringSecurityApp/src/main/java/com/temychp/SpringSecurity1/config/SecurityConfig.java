package com.temychp.SpringSecurity1.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider();
    }
}
