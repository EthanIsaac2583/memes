package kz.ruanjian.memed.service;

import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class VisitService {

  private static final String NOT_FOUND = "Visit not found";

  private final VisitRepository visitRepository;

  public VisitService(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  public Visit findById(UUID id) {
    return visitRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  @Transactional
  public Visit create() {
    Visit visit = Visit.builder()
      .createdAt(ZonedDateTime.now())
      .build();

    return visitRepository.save(visit);
  }
}
