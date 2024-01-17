package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.VisitDto;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitController {

  private final VisitService visitService;

  public VisitController(VisitService visitService) {
    this.visitService = visitService;
  }

  @GetMapping("/{id}")
  public Visit findById(@PathVariable UUID id) {
    return visitService.findById(id);
  }

  @PostMapping
  public Visit create(@RequestBody VisitDto visit) {
    return visitService.create(visit);
  }
}
