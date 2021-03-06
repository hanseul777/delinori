package com.noriteo.delinori.common.security.config;

import com.noriteo.delinori.common.security.handler.CustomAccessDeniedHandler;
import com.noriteo.delinori.common.security.handler.CustomAuthenticationEntryPoint;
import com.noriteo.delinori.common.security.handler.CustomLoginSuccessHandler;
import com.noriteo.delinori.common.security.service.CustomUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Log4j2
@MapperScan(basePackages = "com.noriteo.delinori.common.security.mapper")
@ComponentScan(basePackages = "com.noriteo.delinori.common.security.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/security/doAll").permitAll()
                .antMatchers("/security/doDeli").access("hasRole('ROLE_DELI')")
                .antMatchers("/security/doNori").access("hasRole('ROLE_NORI')")
                .antMatchers("/security/doAdmin").access("hasRole('ROLE_ADMIN')");

        http.formLogin().loginPage("/customLogin")
                .loginProcessingUrl("/login");

//        http.logout().invalidateHttpSession(true); //????????????

        http.csrf().disable();

        http.rememberMe().tokenRepository(persistentTokenRepository())
                .key("delinori")
                .tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint());

        http.formLogin().defaultSuccessUrl("/qna/list");
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint(); //?????? ??????????????? ?????? ??? ????????????.
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
