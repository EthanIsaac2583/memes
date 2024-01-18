package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.service.LeadService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
  public Lead findByUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
    return leadService.findByUsername(userDetails.getUsername());
  }
}
