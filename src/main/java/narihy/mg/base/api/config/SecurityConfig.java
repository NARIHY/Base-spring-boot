package narihy.mg.base.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Désactive la protection CSRF (pour les API REST, par exemple)
                .authorizeRequests()
                .requestMatchers("/**").permitAll()  // Permet l'accès à toutes les routes sans authentification
                .anyRequest().authenticated();  // Toute autre requête nécessite une authentification

        return http.build();
    }
}