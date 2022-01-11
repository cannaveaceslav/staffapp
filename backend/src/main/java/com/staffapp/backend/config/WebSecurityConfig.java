package com.staffapp.backend.config;

import com.staffapp.backend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  //todo remove all restricted pages
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .cors()
            .and()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,
                         "/registration/**",
                         "/js/**",
                         "/css/**",
                         "/img/**",
                         "/login/**",
                         "/layout/**",
                         "/items/**",
                         "/assets/**",
                         "/actuator",
                         "/actuator/*",
                         "/instances",
                         "/instances/*")
            .permitAll()
            .anyRequest()
            .authenticated().and()
            .formLogin();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }

  //  @Bean
//  public CorsFilter corsFilter() {
//    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//    CorsConfiguration corsConfiguration = new CorsConfiguration();
//    corsConfiguration.setAllowCredentials(false);
////    corsConfiguration.setAllowedOrigins((Collections.singletonList("http://localhost:4200")));
//    corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Accec-Control-Allow-Origin",
//                                                      "Content-Type", "Accept", "Jwt-Token",
//                                                      "Authorization", "Origin, Accept", "X-Request-With",
//                                                      "Access-Control-Request-Method", "Access-Control-Request-Headers"));
//    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Tokem",
//                                                      "Authorization", "Access-Control-Allow-Origin",
//                                                      "Access-Control-Allow-Credentials", "Filename"));
//    corsConfiguration.setAllowedHeaders(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//    return new CorsFilter((urlBasedCorsConfigurationSource));
//  }
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
    configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
