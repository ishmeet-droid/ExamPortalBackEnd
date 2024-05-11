package com.practice.examportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.practice.examportal.authentication.JwtAuthenticationEntryPoint;
import com.practice.examportal.authentication.JwtAuthenticationFilter;
import com.practice.examportal.authentication.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

   
    private UserDetailsServiceImpl userDetailService;

    private JwtAuthenticationEntryPoint unauthorizedHandler;

    private PasswordEncoder passwordEncoder;

    private JwtAuthenticationFilter jwtAutheticaticationFilter;

    public MySecurityConfig(UserDetailsServiceImpl userDetailService,
                            PasswordEncoder passwordEncoder,
                            JwtAuthenticationEntryPoint unauthorizedHandler,
                            JwtAuthenticationFilter jwtAutheticaticationFilter) {
       // this.userDetailService = customUserDetailService;
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAutheticaticationFilter = jwtAutheticaticationFilter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/generate-token", "/users/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(t -> t.authenticationEntryPoint(unauthorizedHandler))
                .httpBasic(Customizer.withDefaults());
        
        http.addFilterBefore(jwtAutheticaticationFilter,  UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        
        return daoAuthenticationProvider;
    }

    

}
