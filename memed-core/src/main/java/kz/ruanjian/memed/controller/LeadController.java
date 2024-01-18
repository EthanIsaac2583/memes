package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.service.LeadService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/private/leads")
public class LeadController {

  private final LeadService leadService;

  public LeadController(LeadService leadService) {
    this.leadService = leadService;
  }

  @GetMapping("/me")
  public Lead findByAuthentication() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return leadService.findByUsername(username);
  }
}
