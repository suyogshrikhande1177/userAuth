package com.practice.userAuth.Security;import com.practice.userAuth.Security.jwt.AuthEntryPointJwt;import com.practice.userAuth.Security.jwt.AuthTokenFilter;import com.practice.userAuth.Security.srevices.UserDetailsServiceImpl;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.security.authentication.ProviderManager;import org.springframework.security.authentication.dao.DaoAuthenticationProvider;import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;import org.springframework.security.config.http.SessionCreationPolicy;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.security.web.SecurityFilterChain;import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;import org.springframework.web.cors.CorsConfiguration;import org.springframework.web.cors.CorsConfigurationSource;import org.springframework.web.cors.UrlBasedCorsConfigurationSource;import java.util.List;@Configuration@EnableWebSecurity@EnableMethodSecurity(prePostEnabled = true)public class WebSecurityConfig{    @Autowired    private UserDetailsServiceImpl userDetailsService;    @Autowired    private AuthEntryPointJwt unauthorizedHandler;    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,AuthEntryPointJwt unauthorizedHandler){        this.userDetailsService = userDetailsService;        this.unauthorizedHandler = unauthorizedHandler;    }    @Bean    public AuthTokenFilter authenticationJwtTokenFilter() {        return new AuthTokenFilter();    }    @Bean    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder){        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();        authenticationProvider.setUserDetailsService(userDetailsService);        authenticationProvider.setPasswordEncoder(passwordEncoder);        return new ProviderManager(authenticationProvider);    }    @Bean    public PasswordEncoder passwordEncoder(){        return new BCryptPasswordEncoder();    }    @Bean    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{        http                .csrf(AbstractHttpConfigurer::disable) // CSRF explicitly disabled                .cors(cors -> cors.configurationSource(corsConfigurationSource()))                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))                .authorizeHttpRequests(auth -> auth                        .requestMatchers(                                "/api/auth/**",                                "/v2/api-docs",           // swagger                                "/webjars/**",            // swagger-ui webjars                                "/swagger-resources/**",  // swagger-ui resources                                "/configuration/**",      // swagger configuration                                "/*.html",                                "/favicon.ico",                                "/**/*.html",                                "/**/*.css",                                "/**/*.js"                        ).permitAll()                        .requestMatchers("/signup/**", "/api/auth/**", "/exam/getCurrentDateTime").permitAll()                        .anyRequest().authenticated()                );        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);        return http.build();    }    @Bean    public CorsConfigurationSource corsConfigurationSource() {        CorsConfiguration configuration = new CorsConfiguration();        configuration.setAllowedOrigins(List.of("*")); // Allow all origins (modify as needed)        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));        configuration.setAllowCredentials(true);        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();        source.registerCorsConfiguration("/**", configuration);        return source;    }}