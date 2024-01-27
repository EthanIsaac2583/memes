package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.service.TemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TemplateController {

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @GetMapping("/templates/{id}")
  public Template findById(@PathVariable Long id) {
    return templateService.findById(id);
  }

  @GetMapping("/templates")
  public Page<Template> findAll(Pageable pageable) {
    return templateService.findAll(pageable);
  }

  @PostMapping("/private/templates")
  @PreAuthorize("hasRole('ADMIN')")
  public void create(@RequestBody TemplateDto template) {
    templateService.save(template);
  }
}
