package TicketFlash.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable() // importante para permitir POST desde Postman sin token CSRF
                .authorizeHttpRequests()
                .requestMatchers("/payment/pago-exitoso",
                        "/ticket/cancel/**",
                        "/ticket/create/**",
                        "/event/findById/**",
                        "/api/category-ticket/create",
                        "/payment/create-preference/**",
                        "/pago-exitoso",
                        "/payment/webhook"
                        ,"/tickets/user/{userId}").permitAll() // permití acá lo que necesites
                .anyRequest().authenticated()
                .and()
                .formLogin().disable() // deshabilitá el login con formulario para evitar el HTML en Postman
                .httpBasic() // podés usar autenticación básica desde Postman (username y password)
                .and()
                .build();
    }
}
