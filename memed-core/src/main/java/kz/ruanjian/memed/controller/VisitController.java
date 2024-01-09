package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.VisitDto;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.service.VisitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitController {

  private final VisitService visitService;

  public VisitController(VisitService visitService) {
    this.visitService = visitService;
  }

  @PostMapping
  public Visit create(@RequestHeader("User-Agent") String userAgent,
                      @RequestBody VisitDto visit) {
    visit.setUserAgent(userAgent);
    return visitService.create(visit);
  }
}
