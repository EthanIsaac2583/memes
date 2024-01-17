package kz.ruanjian.memed.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.ruanjian.memed.controller.error.ErrorResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final SecurityManager securityManager;
  private final UserDetailsService userDetailsService;
  private final ObjectMapper objectMapper;

  public SecurityFilter(SecurityManager securityManager,
                        UserDetailsService userDetailsService,
                        ObjectMapper objectMapper) {
    this.securityManager = securityManager;
    this.userDetailsService = userDetailsService;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader("Authorization");

      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }

      String token = authorizationHeader.substring(7);
      String username = securityManager.getUsername(token);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (securityManager.isValidToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }

      filterChain.doFilter(request, response);
    } catch (JwtException exception) {
      ErrorResponse errorResponse = ErrorResponse.builder()
        .statusCode(HttpServletResponse.SC_FORBIDDEN)
        .message(exception.getMessage())
        .build();

      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setHeader("Content-Type", "application/json");
      response.getOutputStream().write(objectMapper.writeValueAsBytes(errorResponse));
    }
  }
}
