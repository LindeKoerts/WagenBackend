package com.wagengaragebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.sql.DataSource;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    private DataSource dataSource;


    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    WebSecurity(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }



    @Autowired
    public void  configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication().dataSource( dataSource);
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/cars/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/customers/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/carjobs/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/joboperations/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/jobparts/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/carjobinvoices/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/authenticate/**").permitAll()
                .antMatchers("/api/v1/parts/**").hasRole("ADMIN")
                .antMatchers("/api/v1/operations/**").hasRole("ADMIN")
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
                .antMatchers("/api/v1/files/**").hasAnyRole("ADMIN","USER")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
