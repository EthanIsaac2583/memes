package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/templates")
public class TemplateController {

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @GetMapping("/{id}")
  public Template findById(@PathVariable Long id) {
    return templateService.findById(id);
  }

  @PostMapping
  public void create(@RequestBody TemplateDto template) {
    templateService.save(template);
  }
}
