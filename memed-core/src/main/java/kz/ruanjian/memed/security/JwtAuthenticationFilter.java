package kz.ruanjian.memed.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

    if (isAuthorizationHeaderNotPresent(authorizationHeader)) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = getToken(authorizationHeader);
  }

  private boolean isAuthorizationHeaderNotPresent(String authorizationHeader) {
    return authorizationHeader == null || authorizationHeader.startsWith(BEARER_PREFIX);
  }

  private String getToken(String authorizationHeader) {
    return authorizationHeader.substring(BEARER_PREFIX.length());
  }
}
