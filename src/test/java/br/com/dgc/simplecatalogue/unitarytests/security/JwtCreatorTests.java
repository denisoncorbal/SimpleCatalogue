package br.com.dgc.simplecatalogue.unitarytests.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.dgc.simplecatalogue.security.JwtCreator;
import br.com.dgc.simplecatalogue.security.JwtObject;
import br.com.dgc.simplecatalogue.security.SecurityConfig;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class JwtCreatorTests {
  final private SecurityConfig securityConfig = new SecurityConfig(
          "Bearer",
          "2e7a58d9-1d28-421a-a2c9-75dd0490c1b3-bf9e7d16-1851-4e25-b98d-3360680da07a",
          60000L, null);
  final private JwtObject jwtObject = JwtObject.builder()
      .subject("0")
      .issuedAt(new Date((System.currentTimeMillis())))
      .expiration(new Date(System.currentTimeMillis() + securityConfig.expiration))
      .roles(List.of("ADMIN"))
      .build();

  private static String validToken = "";
  private static String expiredToken = "";

  @Test
  @Order(1)
  public void givenAuthentication_whenLoginSucessfull_thenReturnStringToken(){

    validToken = JwtCreator.create(securityConfig.prefix,
      securityConfig.secretKey,
      jwtObject);

    jwtObject.setExpiration(jwtObject.getIssuedAt());

    expiredToken = JwtCreator.create(securityConfig.prefix,
        securityConfig.secretKey,
        jwtObject);

    assertNotNull(validToken);
    assertFalse(validToken.isEmpty());
    assertFalse(validToken.isBlank());


  }

  @Test
  @Order(2)
  public void givenAuthentication_whenTokenValid_thenReturnJWTObject(){
    JwtObject validJwtObject = JwtCreator.create(validToken, securityConfig.prefix, securityConfig.secretKey);

    assertNotNull(validJwtObject);
  }

  @Test
  @Order(3)
  public void givenAuthentication_whenExpiredToken_thenThrow(){
    assertThrows(ExpiredJwtException.class, () -> JwtCreator.create(expiredToken, securityConfig.prefix, securityConfig.secretKey));
  }

}
