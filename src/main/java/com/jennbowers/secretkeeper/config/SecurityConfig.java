package com.jennbowers.secretkeeper.config;

import com.jennbowers.secretkeeper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //    tells security that the userservice they have is the one we implemented, how to find their users
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        ignore all security in the assets directory
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                this is what happens when you log in
//                tells it to apply this to everything
                .authorizeRequests()
//                    .anyRequest().hasRole("USER")
                    .antMatchers("/").permitAll()
                    .antMatchers("/secret/**").hasRole("USER")
                    .and()
//                handles what happens when you login
                .formLogin()
                    .loginPage("/login")
                    .successHandler(loginSuccessHandler())
                    .failureHandler(loginFailureHandler())
                    .and()
//                handles what happens when you logout
                .logout()
                    .permitAll()
                    .logoutSuccessUrl("/login");


    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
//        lets redirect them with this called function
        return(request, response, authentication) -> response.sendRedirect("/");
    }

    private AuthenticationFailureHandler loginFailureHandler() {
//        how to handle login failure
        return(request, response, exception) -> {
            request.getSession().setAttribute("error", "Cannot login with provided credentials");
            response.sendRedirect("/login");
        };
    }





}
