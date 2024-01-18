package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

  private static final String NOT_FOUND = "Lead not found";

  private final LeadRepository leadRepository;

  public LeadService(LeadRepository leadRepository) {
    this.leadRepository = leadRepository;
  }

  public Lead findByUsername(String username) {
    return leadRepository
      .findByUsername(username)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }
}
