package com.skdvp.app.security;

import com.skdvp.app.security.handler.SuccessUserHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям


    public SecurityConfig(@Qualifier("securityServiceImpl") UserDetailsService userDetailsService,
                          SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.logout()

                // разрешаем делать логаут всем
                .permitAll()

                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login");



        http

                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/").permitAll() // доступность всем
//                .antMatchers("/login").anonymous()
                .antMatchers("/index").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .successHandler(successUserHandler);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
