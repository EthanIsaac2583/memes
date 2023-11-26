package kz.ruanjian.memed.service;

import kz.ruanjian.memed.leadquizanswerchecker.LeadQuizAnswerCheckerContext;
import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.LeadQuizAnswerRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadQuizAnswerService {

  private static final String NOT_FOUND_EXCEPTION = "Quiz answer not found";

  private final LeadQuizAnswerRepository leadQuizAnswerRepository;
  private final LeadQuizAnswerMapper leadQuizAnswerMapper;
  private final LeadQuizAnswerCheckerContext leadQuizAnswerCheckerContext;

  public LeadQuizAnswerService(LeadQuizAnswerRepository leadQuizAnswerRepository,
                               LeadQuizAnswerMapper leadQuizAnswerMapper,
                               LeadQuizAnswerCheckerContext leadQuizAnswerCheckerContext) {
    this.leadQuizAnswerRepository = leadQuizAnswerRepository;
    this.leadQuizAnswerMapper = leadQuizAnswerMapper;
    this.leadQuizAnswerCheckerContext = leadQuizAnswerCheckerContext;
  }

  public Question findById(Long id) {
    return leadQuizAnswerRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION));
  }

  @Transactional
  public Question provide(QuestionProvideDto provideDto) {
    Question answer = findById(provideDto.getId());
    leadQuizAnswerMapper.applyAnswer(answer, provideDto);

    int autoGrade = leadQuizAnswerCheckerContext.check(answer);
    answer.setGrade(autoGrade);

    leadQuizAnswerRepository.save(answer);

    return answer;
  }

  @Transactional
  public Question grade(QuestionGradeDto gradeDto) {
    Question answer = findById(gradeDto.getId());
    leadQuizAnswerMapper.applyGrade(answer, gradeDto);

    leadQuizAnswerRepository.save(answer);

    return answer;
  }
}
