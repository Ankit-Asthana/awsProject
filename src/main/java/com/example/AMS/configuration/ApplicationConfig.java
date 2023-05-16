package com.example.AMS.configuration;


import com.example.AMS.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserDao userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            return userRepository.findByEmail(username);
        };
        //.orElseThrow(()->new UsernameNotFoundException("User Not found"));
    }

    //it is a data access object used to fetch the userdetails and encode password etc.
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();//here we're using the DaoAuthenticationProvider
        authenticationProvider.setUserDetailsService(userDetailsService());//specify which user details we're using
        authenticationProvider.setPasswordEncoder(passwordEncoder());//specify which password encoder we're using
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
       //return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}