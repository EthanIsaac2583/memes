package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.LeadQuizAnswerGradeDto;
import kz.ruanjian.memed.dto.LeadQuizAnswerProvideDto;
import kz.ruanjian.memed.mapper.LeadQuizAnswerMapper;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import kz.ruanjian.memed.respository.LeadQuizAnswerRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadQuizAnswerService {

  private static final String NOT_FOUND_EXCEPTION = "Quiz answer not found";

  private final LeadQuizAnswerRepository leadQuizAnswerRepository;
  private final LeadQuizAnswerMapper leadQuizAnswerMapper;

  public LeadQuizAnswerService(LeadQuizAnswerRepository leadQuizAnswerRepository,
                               LeadQuizAnswerMapper leadQuizAnswerMapper) {
    this.leadQuizAnswerRepository = leadQuizAnswerRepository;
    this.leadQuizAnswerMapper = leadQuizAnswerMapper;
  }

  public LeadQuizAnswer findById(Long id) {
    return leadQuizAnswerRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  @Transactional
  public LeadQuizAnswer provide(LeadQuizAnswerProvideDto provideDto) {
    LeadQuizAnswer answer = findById(provideDto.getId());
    leadQuizAnswerMapper.applyAnswer(answer, provideDto);

    leadQuizAnswerRepository.save(answer);

    return answer;
  }

  @Transactional
  public LeadQuizAnswer grade(LeadQuizAnswerGradeDto gradeDto) {
    LeadQuizAnswer answer = findById(gradeDto.getId());
    leadQuizAnswerMapper.applyGrade(answer, gradeDto);

    leadQuizAnswerRepository.save(answer);

    return answer;
  }
}
