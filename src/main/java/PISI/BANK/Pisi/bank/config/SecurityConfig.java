package PISI.BANK.Pisi.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()  // Updated method name
                .requestMatchers("/dashboard").authenticated() // Protect /dashboard
                .anyRequest().permitAll() // Allow other endpoints
                .and()
                .formLogin(form -> form
                        .loginPage("/login")  // Optional, specify custom login page URL
                        .defaultSuccessUrl("/dashboard", true)  // After login, redirect to /dashboard
                        .permitAll() // Allow everyone to see the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")  // Redirect after logout
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in dev

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}