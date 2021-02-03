package com.board.foo.config;

import com.board.foo.handler.CustomAuthFailureHandler;
import com.board.foo.handler.CustomAuthSuccessHandler;
import com.board.foo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
            "/images/**", "/css/**", "/img/**", "/js/**",
            "/console/**", "/favicon.ico/**", "/assets/**",
            "/dist/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/members/**", "/", "/login", "/boards/**").permitAll() // boards/**는 임시
                    .anyRequest().authenticated()
                    .and()
            .csrf()
                    .disable()
            .headers()
                    .frameOptions().disable()
                    .and()
            .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                     .failureUrl("/login?error=true")
                    .successHandler(successHandler())
                    .failureHandler(failureHandler())
            .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }

}
