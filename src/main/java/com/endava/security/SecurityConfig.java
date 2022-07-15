package com.endava.security;

import com.endava.filters.JwtRequestFilter;
import com.endava.services.UserDtoDetailsService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

import static com.endava.utils.Constants.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDtoDetailsService userDtoDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDtoDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // disable csrf for now, but it should be enabled because it protects from attacks
                .authorizeRequests().antMatchers("/api/users/register").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/users/confirm?**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/users/login").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/users/verify/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(
                        "/swagger-ui/index.html/**",
                        "/api-docs",
                        "/swagger-ui/swagger-ui.css",
                        "/swagger-ui/swagger-ui-bundle.js",
                        "/swagger-ui/swagger-ui-standalone-preset.js",
                        "/swagger-ui/favicon-32x32.png",
                        "/swagger-ui/favicon-16x16.png",
                        "/api-docs/swagger-config")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme(SECURITY_SCHEME).bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name(ACCESS_TOKEN_HEADER)))
                .info(new Info().title("Book Sharing Application API").version("snapshot"))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
