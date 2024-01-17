package kz.ruanjian.memed.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kz.ruanjian.memed.config.MemedProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class SecurityManager {

  private final MemedProperties memedProperties;

  public SecurityManager(MemedProperties memedProperties) {
    this.memedProperties = memedProperties;
  }

  public String getUsername(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public String generateToken(UserDetails userDetails) {
    return Jwts
      .builder()
      .setSubject(userDetails.getUsername())
      .setIssuedAt(generateDateFromNow(0L))
      .setExpiration(generateDateFromNow(memedProperties.getSecurityExpirationInMs()))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean isValidToken(String token, UserDetails userDetails) {
    String username = getUsername(token);

    return !isTokenExpired(token) && userDetails.getUsername().equals(username);
  }

  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(generateDateFromNow(0L));
  }

  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  private Claims getClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private <T> T getClaim(String token, Function<Claims, T> resolver) {
    return resolver.apply(getClaims(token));
  }

  private Key getSignInKey() {
    byte[] decoded = Decoders.BASE64.decode(memedProperties.getSecuritySecret());
    return Keys.hmacShaKeyFor(decoded);
  }

  private Date generateDateFromNow(long additionMilliseconds) {
    return new Date(System.currentTimeMillis() + additionMilliseconds);
  }
}
