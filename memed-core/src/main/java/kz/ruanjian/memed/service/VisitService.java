package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.VisitDto;
import kz.ruanjian.memed.mapper.VisitMapper;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VisitService {

  private static final String NOT_FOUND = "Visit not found";

  private final VisitRepository visitRepository;
  private final VisitMapper visitMapper;

  public VisitService(VisitRepository visitRepository, VisitMapper visitMapper) {
    this.visitRepository = visitRepository;
    this.visitMapper = visitMapper;
  }

  public Visit findById(UUID id) {
    return visitRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  @Transactional
  public Visit create(VisitDto visitDto) {
    Visit visit = visitMapper.toVisit(visitDto);
    visitRepository.save(visit);

    return visit;
  }
}
