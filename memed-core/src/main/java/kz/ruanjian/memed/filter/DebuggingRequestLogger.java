package kz.ruanjian.memed.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.ruanjian.memed.util.LogbackMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DebuggingRequestLogger extends OncePerRequestFilter {

  private static Logger log = LoggerFactory.getLogger(DebuggingRequestLogger.class);
  private static Marker internalMarker = MarkerFactory.getMarker(LogbackMarker.INTERNAL.label);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (log.isInfoEnabled()) {
      log.info(internalMarker, "[GET] /api/v1/templates Headers:{} Ip:{}", getAllHeaders(request), getRemoteIp(request));
    }
    filterChain.doFilter(request, response);
  }

  public static Map<String, String> getAllHeaders(HttpServletRequest request) {
    Enumeration<String> headerNames = request.getHeaderNames();
    Map<String, String> headers = new HashMap<>();

    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      headers.put(headerName, headerValue);
    }

    return headers;
  }

  private String getRemoteIp(HttpServletRequest request) {
    return request.getRemoteAddr();
  }
}
