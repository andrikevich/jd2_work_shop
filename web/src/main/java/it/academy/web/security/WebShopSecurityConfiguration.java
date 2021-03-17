package it.academy.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebShopSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //определяем пароль енкодер
//    @Bean
//       public BCryptPasswordEncoder passwordEncoder(){
//           return new BCryptPasswordEncoder();
//       }


    // enable in memory based authentication with a user named "user" and "admin"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //.passwordEncoder(passwordEncoder())
                .withUser("test") .password("{noop}test123").roles("USER")

                //.password("{bcrypt}$2a$10$8ZZk1SuhG/PO7Okw3wR0/u0v6SROgee2Xs4Zp3pIyd6nmFS32G8IG").roles("USER")
                .and()
                .withUser("admin").password("{noop}test123").roles("USER", "ADMIN");
               // .password("{bcrypt}$2a$10$8ZZk1SuhG/PO7Okw3wR0/u0v6SROgee2Xs4Zp3pIyd6nmFS32G8IG").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/product").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .formLogin()
        .and()
        .logout().permitAll();
        ;

    }
}
