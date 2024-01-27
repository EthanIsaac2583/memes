package kz.ruanjian.memed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@DataSet(value = "dataset/template/initial.yml", strategy = SeedStrategy.INSERT)
class TemplateComponentTest extends AbstractComponentTest {

  private static final String URI = "/api/v1/templates";

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
  void findAll_shouldReturnTemplatePage_whenRequested() {
    webTestClient.get()
      .uri(URI)
      .exchange()
      .expectStatus()
      .isForbidden();
  }
}
