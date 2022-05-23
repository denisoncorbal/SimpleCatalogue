package br.com.dgc.simplecatalogue.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Global configuration for routes with obligated authentication.
 *
 * @see SecurityConfig
 * @see HttpSecurity
 * @see WebSecurityConfigurerAdapter
 * @since 1.0
 */

@EnableWebSecurity
public class WebSecurityConfig {
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private JwtUserDetailsService jwtUserDetailsService;
  private JwtFilter jwtFilter;

  public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
      JwtUserDetailsService jwtUserDetailsService,
      JwtFilter jwtFilter){
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain web(HttpSecurity httpSecurity) throws Exception{
    return httpSecurity.csrf().disable()
        .authorizeHttpRequests().antMatchers("/api/v1/login").permitAll()
        .antMatchers("/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return new AuthenticationManagerBuilder(
        new ObjectPostProcessor<Object>() {
          @Override
          public <O extends Object> O postProcess(O object) {
            return object;
          }
        }).userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder()).and().build();
  };
}