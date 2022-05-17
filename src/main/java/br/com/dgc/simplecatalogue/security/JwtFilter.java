package br.com.dgc.simplecatalogue.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter responsible for check if the requisitions are authenticated.
 *
 * @see JwtObject
 * @see WebSecurityConfig
 * @see JwtCreator
 * @since 1.0
 */
public class JwtFilter extends OncePerRequestFilter {
  private final SecurityConfig securityConfig;

  public JwtFilter(SecurityConfig securityConfig) {
    this.securityConfig = securityConfig;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filter)
      throws ServletException, IOException {
    String token = request.getHeader(JwtCreator.HEADER_AUTHORIZATION);
    try {
      if (token != null && !token.isEmpty()) {
        JwtObject tokenObject =
            JwtCreator.create(token, securityConfig.prefix, securityConfig.getSecretKey());

        List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());

        UsernamePasswordAuthenticationToken userToken =
            new UsernamePasswordAuthenticationToken(tokenObject.getSubject(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(userToken);
      } else {
        SecurityContextHolder.clearContext();
      }
      filter.doFilter(request, response);
    } catch (ExpiredJwtException
        | UnsupportedJwtException
        | MalformedJwtException
        | SignatureException e) {
      e.printStackTrace();
      response.setStatus(HttpStatus.FORBIDDEN.value());
    }
  }

  private List<SimpleGrantedAuthority> authorities(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
