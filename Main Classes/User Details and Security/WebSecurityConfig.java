package com.example.nesdeneme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/list_users").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/newUser").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/updateUser").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("deleteUser").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/doctorList").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/new").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("update").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/delete").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/questions").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
           /*     .antMatchers("/sss").hasAnyAuthority("CREATOR","USER","ADMIN","EDITOR")
                .antMatchers("/appoints").hasAnyAuthority("CREATOR","USER","ADMIN","EDITOR")
                .antMatchers("/newAppointment").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/newCall").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/login").hasAnyAuthority("CREATOR","USER","ADMIN","EDITOR")*/
                .antMatchers("/addQuestion").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/appointmentList").hasAnyAuthority("CREATOR","ADMIN","EDITOR")
                .antMatchers("/showCallDoctorList").hasAnyAuthority("CREATOR","ADMIN","EDITOR")

                .and()
                .formLogin().loginPage("/login").usernameParameter("email")
                .defaultSuccessUrl("/index").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                ;

    }


}
