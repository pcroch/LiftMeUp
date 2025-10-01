package api.liftMeUp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/v1/elevator/call").permitAll() // Access for everyone
//                .antMatchers("/api/v1/elevator/priority").hasRole("ADMIN") // Access only for Fireman
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}