package br.com.dgc.simplecatalogue.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.SecretKey;

/**
 * Responsible for create JWT tokens and for verify JWT tokens.
 *
 * @see JwtObject
 * @since 1.0
 */
public class JwtCreator {
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String ROLES_AUTHORITIES = "authorities";

  /**
   * Create a Jwt token from a JwtObject.
   *
   * @param prefix Prefix used for Authorization header. Default "Bearer" for Bearer schema.
   * @param secretKey Key used for sign purpose.
   * @param jwtObject Object containing information to be set on payload of Jwt token.
   * @return Jwt token as String to be used on next requisitions on this API.
   * @see JwtObject
   * @see java.security.Key
   * @since 1.0
   */
  public static String create(String prefix, SecretKey secretKey, JwtObject jwtObject) {
    String token =
        Jwts.builder()
            .setSubject(jwtObject.getSubject())
            .setIssuedAt(jwtObject.getIssuedAt())
            .setExpiration(jwtObject.getExpiration())
            .claim(
                ROLES_AUTHORITIES,
                jwtObject.getRoles().stream().map(s -> "ROLE_" + s).collect(Collectors.toList()))
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact();
    return prefix + " " + token;
  }

  /**
   * Recover the payload from a signed token as JwtObject.
   *
   * @param token Token as string to be verified.
   * @param prefix Prefix used for Authorization header. Default "Bearer" for Bearer schema.
   * @param secretKey Key used for sign purpose.
   * @return JwtObject containing the payload from token passed.
   * @throws ExpiredJwtException Thrown when the expiration date on token has been passed.
   * @throws UnsupportedJwtException Thrown when the token is formatted differently than expected.
   * @throws MalformedJwtException Thrown when the token is malformed being impossible to
   *     deserialize it.
   * @throws SignatureException Thrown when the token has an invalid signature.
   */
  public static JwtObject create(String token, String prefix, SecretKey secretKey)
      throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
          SignatureException {
    token = token.replace(prefix, "");
    Claims claims =
        Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    return JwtObject.builder()
        .subject(claims.getSubject())
        .issuedAt(claims.getIssuedAt())
        .expiration(claims.getExpiration())
        .roles(
            Stream.of(claims.get(ROLES_AUTHORITIES))
                .map(Object::toString)
                .collect(Collectors.toList()))
        .build();
  }
}
