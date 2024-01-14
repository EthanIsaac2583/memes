package kz.ruanjian.memed.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kz.ruanjian.memed.model.Lead;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

  private static final String SECRET = "A1B2C3D4E5F6A7B8C9D0E1F2A3B4C5D6E7F8A9B0C1D2E3F4A5B6C7D8E9F0";
  private static final Long EXPIRE_AFTER_IN_MILLISECONDS = 60_000L;

  public String getUsername(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public String generateToken(UserDetails userDetails) {
    return Jwts
      .builder()
      .setSubject(userDetails.getUsername())
      .setIssuedAt(generateDateFromNow(0L))
      .setExpiration(generateDateFromNow(EXPIRE_AFTER_IN_MILLISECONDS))
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
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
  }

  private Date generateDateFromNow(long additionMilliseconds) {
    return new Date(System.currentTimeMillis() + additionMilliseconds);
  }
}
