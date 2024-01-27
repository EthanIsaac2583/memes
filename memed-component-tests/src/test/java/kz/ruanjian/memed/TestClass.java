package kz.ruanjian.memed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentsTest;
import kz.ruanjian.memed.model.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@DataSet(value = "dataset/lead/initial.yml", strategy = SeedStrategy.INSERT)
class TestClass extends AbstractComponentsTest {

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
  void test1() {
    webTestClient.get()
      .uri(URL)
      .exchange()
      .expectStatus()
      .isOk();
  }
}
