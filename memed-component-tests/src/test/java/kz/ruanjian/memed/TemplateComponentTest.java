package kz.ruanjian.memed;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentTest;
import kz.ruanjian.memed.model.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

@DataSet(value = "dataset/template/initial.yml", strategy = SeedStrategy.INSERT)
class TemplateComponentTest extends AbstractComponentTest {

  private static final String URI = "/api/v1/templates?number={number}&size={size}";

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
  void findAll_shouldReturnTemplatePage_whenRequested() throws JsonProcessingException {
    Pageable pageable = PageRequest.of(0, 20);
    Page<Template> expected = new PageImpl<>(Collections.singletonList(getFirstTemplate()), pageable, 1);

    webTestClient.get()
      .uri(URI, pageable.getPageNumber(), pageable.getPageSize())
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .json(asString(expected));
  }

  private String asString(Object value) throws JsonProcessingException {
    return objectMapper.writeValueAsString(value);
  }

  private Template getFirstTemplate() {
    return Template.builder()
      .id(1L)
      .name("Unlimited template")
      .description("No limit")
      .limit(0)
      .build();
  }
}
