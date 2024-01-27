package kz.ruanjian.memed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentTest;
import kz.ruanjian.memed.controller.error.ErrorResponse;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@DataSet(value = "dataset/lead/initial.yml", strategy = SeedStrategy.INSERT)
class LeadComponentTest extends AbstractComponentTest {

  private static final String URI = "api/v1/private/leads/me";

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
      .uri(URI)
      .exchange()
      .expectStatus()
      .isForbidden();
  }

  @Test
  void findMe_shouldReturnErrorResponse_whenExpiredTokenPassed() {
    webTestClient.get()
      .uri(URI)
      .header("Authorization", "Bearer " + getExpiredAuthToken())
      .exchange()
      .expectStatus()
      .isForbidden();
  }

  @Test
  void findMe_shouldReturnLead_whenValidAuthorizationPassed() throws JsonProcessingException {
    String authToken = getAuthToken();

    webTestClient.get()
      .uri(URI)
      .header("Authorization", "Bearer " + authToken)
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .json(asString(Lead.builder()
        .id(1L)
        .username("user001")
        .visit(Visit.builder()
          .id(UUID.fromString("e21a5e4f-925e-4384-af3f-0050d49f10c9"))
          .build())
        .build()));
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }
}
