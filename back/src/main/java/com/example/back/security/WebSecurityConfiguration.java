package com.example.back.security;

import com.example.back.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.stream.Stream;

@Configuration @EnableWebSecurity
public class WebSecurityConfiguration {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var USER = AuthorityAuthorizationManager.<RequestAuthorizationContext>hasRole("USER");
        var HOST = AuthorityAuthorizationManager.<RequestAuthorizationContext>hasRole("HOST");
        var ADMIN = AuthorityAuthorizationManager.<RequestAuthorizationContext>hasRole("ADMIN");
        Stream.of(USER, HOST, ADMIN).forEach(role -> role.setRoleHierarchy(roleHierarchy()));

        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                    // GET permitted for all //
                                    .requestMatchers(HttpMethod.GET).permitAll()
                                    // ROLE_ADMIN //
                                    .requestMatchers("/caracteristicas/**/").access(ADMIN)
                                    .requestMatchers("/ciudades/**/").access(ADMIN)
                                    .requestMatchers("/categorias/**/").access(ADMIN)
                                    .requestMatchers("/usuarios/roles/**/").access(ADMIN)
                                    // ROLE_HOST //
                                    .requestMatchers("/productos/**/").access(HOST)
                                    .requestMatchers("/imagenes/**/").access(HOST)
                                    .requestMatchers("/politicas/**/").access(HOST)
                                    // AUTHENTICATED / ROLE_USER //
                                    .requestMatchers("/reservas/**/").authenticated()
                                    .requestMatchers("/puntuaciones/**/").authenticated()
                                    .requestMatchers("/favoritos/**/").authenticated()
                                    // PERMIT ALL //
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/usuarios/**/").permitAll()

                                    .anyRequest().authenticated()
                                    .and().sessionManagement()
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_HOST \n ROLE_HOST > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
//        config.addExposedHeader("Authorization"); //Expone el header en la respuesta
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}
