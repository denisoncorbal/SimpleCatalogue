package br.com.dgc.simplecatalogue.unitarytests.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
public class JWTCreatorTests {
  final private SecurityConfig securityConfig = new SecurityConfig(
          "Bearer",
          "2e7a58d9-1d28-421a-a2c9-75dd0490c1b3-bf9e7d16-1851-4e25-b98d-3360680da07a",
          60000L);
  final private JWTObject jwtObject = JWTObject.builder()
      .subject("0")
      .issuedAt(new Date((System.currentTimeMillis())))
      .expiration(new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION()))
      .roles(List.of("ADMIN"))
      .build();

  private static String validToken = "";
  private static String expiredToken = "";

  @Test
  @Order(1)
  public void givenAuthentication_whenLoginSucessfull_thenReturnStringToken(){

    validToken = JWTCreator.create(securityConfig.getPREFIX(),
      securityConfig.getSECRET_KEY(),
      jwtObject);

    jwtObject.setExpiration(jwtObject.getIssuedAt());

    expiredToken = JWTCreator.create(securityConfig.getPREFIX(),
        securityConfig.getSECRET_KEY(),
        jwtObject);

    assertNotNull(validToken);
    assertFalse(validToken.isEmpty());
    assertFalse(validToken.isBlank());


  }

  @Test
  @Order(2)
  public void givenAuthentication_whenTokenValid_thenReturnJWTObject(){
    JWTObject validJWTObject = JWTCreator.create(validToken, securityConfig.getPREFIX(), securityConfig.getSECRET_KEY());

    assertNotNull(validJWTObject);
  }

  @Test
  @Order(3)
  public void givenAuthentication_whenExpiredToken_thenThrow(){
    assertThrows(ExpiredJwtException.class, () -> JWTCreator.create(expiredToken, securityConfig.getPREFIX(), securityConfig.getSECRET_KEY()));
  }

}
