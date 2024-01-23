package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.service.exception.DataConflictException;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LeadService {

  private static final String NOT_FOUND = "Lead not found";
  private static final String LEAD_EXISTS = "Lead already exists";

  private final LeadRepository leadRepository;

  public LeadService(LeadRepository leadRepository) {
    this.leadRepository = leadRepository;
  }

  public Lead findByUsername(String username) {
    return leadRepository
      .findByUsername(username)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  @Transactional
  public Lead save(Lead lead) {
    verify(lead);

    return leadRepository.save(lead);
  }

  private void verify(Lead lead) {
    verifyUsernameIsUnique(lead);
  }

  private void verifyUsernameIsUnique(Lead lead) {
    leadRepository
      .findByUsername(lead.getUsername())
      .filter(persistedLead -> !isEqualById(lead, persistedLead))
      .ifPresent(persistedLead -> {
        throw new DataConflictException(LEAD_EXISTS);
      });
  }

  private boolean isEqualById(Lead lead, Lead persistedLead) {
    return persistedLead.getId().equals(lead.getId());
  }
}
