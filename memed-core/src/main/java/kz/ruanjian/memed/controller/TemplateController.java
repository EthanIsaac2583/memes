package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.service.TemplateService;
import kz.ruanjian.memed.util.LogbackMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/templates")
public class TemplateController {

  private static Logger log = LoggerFactory.getLogger(TemplateController.class);
  private static Marker appInteralMarker = MarkerFactory.getMarker(LogbackMarker.Internal.label);

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @GetMapping("/{id}")
  public Template findById(@PathVariable Long id) {
    return templateService.findById(id);
  }

  @GetMapping
  public Page<Template> findAll(@RequestHeader Map<String, String> headers,
                                Pageable pageable) {
    if (log.isInfoEnabled()) {
      log.info(appInteralMarker, "[GET] /api/v1/templates. {}", headers);
    }

    return templateService.findAll(pageable);
  }

  @PostMapping
  public void create(@RequestBody TemplateDto template) {
    templateService.save(template);
  }
}
