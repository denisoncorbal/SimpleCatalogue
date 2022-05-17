package br.com.dgc.simplecatalogue.unitarytests.security;

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

public class JWTCreator {
  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String ROLES_AUTHORITIES = "authorities";

  public static String create(String prefix, SecretKey secretKey, JWTObject jwtObject){
    String token = Jwts.builder()
        .setSubject(jwtObject.getSubject())
        .setIssuedAt(jwtObject.getIssuedAt())
        .setExpiration(jwtObject.getExpiration())
        .claim(ROLES_AUTHORITIES, jwtObject.getRoles().stream().map(s -> "ROLE_" + s).collect(
            Collectors.toList()))
        .signWith(secretKey, SignatureAlgorithm.HS512)
        .compact();
    return prefix + " " + token;
  }

  public static JWTObject create(String token, String prefix, SecretKey secretKey)
      throws ExpiredJwtException,
      UnsupportedJwtException,
      MalformedJwtException,
      SignatureException {
    token = token.replace(prefix, "");
    Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    return JWTObject.builder()
        .subject(claims.getSubject())
        .issuedAt(claims.getIssuedAt())
        .expiration(claims.getExpiration())
        .roles(Stream.of(claims.get(ROLES_AUTHORITIES))
                .map(Object::toString)
                .collect(Collectors.toList()))
        .build();
  }
}
