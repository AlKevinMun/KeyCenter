package com.proyect.keycenter.security;

import com.proyect.keycenter.filters.JWTFilter;
import com.proyect.keycenter.service.UserService;
import com.proyect.keycenter.util.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Configura la seguridad de la aplicación utilizando Spring Security para proteger las rutas y manejar la autenticación.
 * Implementa CORS (Cross-Origin Resource Sharing) para permitir solicitudes desde diferentes dominios.
 *
 * @author Alejandro Parrilla Ruiz
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    /**
     * Servicio que proporciona operaciones relacionadas con el usuario.
     */
    @Autowired
    UserService userService;

    /**
     * Entrypoint personalizado para manejar excepciones de autenticación.
     */
    @Autowired
    private JwtAuthenticationEntryPoint point;


    /**
     * Filtro personalizado para manejar la autenticación basada en JWT.
     */
    @Autowired
    private JWTFilter jwtAuthenticationFilter;

    /**
     * Bean que proporciona un codificador de contraseñas usando BCrypt.
     * @return Un objeto {@link PasswordEncoder} que utiliza BCrypt para codificar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean que proporciona un {@link AuthenticationManager} a través de la configuración de autenticación.
     * @param builder La configuración de autenticación utilizada para obtener el {@link AuthenticationManager}.
     * @return El {@link AuthenticationManager} obtenido de la configuración de autenticación.
     * @throws Exception Si ocurre algún error al obtener el {@link AuthenticationManager}.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    /**
     * Bean que proporciona un {@link AuthenticationProvider} que utiliza el servicio de detalles del usuario y un codificador de contraseñas.
     * @return Un objeto {@link AuthenticationProvider} configurado con el servicio de detalles del usuario y el codificador de contraseñas.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean que configura la cadena de filtros de seguridad para proteger las rutas de la aplicación.
     * @param http El objeto {@link HttpSecurity} utilizado para configurar la seguridad.
     * @return Una instancia de {@link SecurityFilterChain} construida con la configuración especificada.
     * @throws Exception Si ocurre algún error durante la configuración de la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authManagerRequestMatcherRegistry ->
                        authManagerRequestMatcherRegistry
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                                .anyRequest().authenticated())
                //.formLogin(flc -> flc//.loginPage("/login")
                //                      .usernameParameter("email")
                //                      .passwordParameter("password")
                //                      .permitAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecurityConfigurer -> httpSecurityConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(httpSecurityConfigurer -> httpSecurityConfigurer
                        .authenticationEntryPoint(point))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Bean que proporciona una fuente de configuración CORS para permitir solicitudes desde diferentes orígenes.
     * @return Un objeto {@link CorsConfigurationSource} configurado para permitir solicitudes desde localhost en los métodos GET, POST y DELETE.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET","POST", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}