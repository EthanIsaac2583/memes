package kz.ruanjian.memed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentTest;
import kz.ruanjian.memed.controller.error.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@DataSet(value = "dataset/lead/initial.yml", strategy = SeedStrategy.INSERT)
class LeadComponentTest extends AbstractComponentTest {

  static final String URL = "api/v1/private/leads/me";

  @Autowired
  ObjectMapper objectMapper;

  WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    webTestClient = WebTestClient.bindToServer()
      .baseUrl(getServiceUrl(""))
      .build();
  }

  @Test
  void findMe_shouldReturnErrorResponse_whenAuthorizationNotPassed() {
    webTestClient.get()
      .uri(URL)
      .exchange()
      .expectStatus()
      .isForbidden()
      .expectBody(ErrorResponse.class)
      .isEqualTo(ErrorResponse.builder()
        .statusCode(403)
        .message("Full authentication is required to access this resource")
        .build());
  }
}
