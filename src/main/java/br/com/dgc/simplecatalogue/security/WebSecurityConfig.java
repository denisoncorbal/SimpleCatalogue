package br.com.dgc.simplecatalogue.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Global configuration for routes with obligated authentication.
 *
 * @see SecurityConfig
 * @see HttpSecurity
 * @see WebSecurityConfigurerAdapter
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  private final SecurityConfig securityConfig;

  public WebSecurityConfig(SecurityConfig securityConfig) {
    this.securityConfig = securityConfig;
  }

  private static final String[] SWAGGER_WHITELIST = {
    "/v2/api-docs",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui.html",
    "/webjars/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable();
    http.cors()
        .and()
        .csrf()
        .disable()
        .addFilterAfter(new JwtFilter(securityConfig), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests()
        .antMatchers(SWAGGER_WHITELIST)
        .permitAll()
        .antMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .antMatchers("/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
