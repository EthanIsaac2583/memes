package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.QuizTemplateDto;
import kz.ruanjian.memed.model.QuizTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizTemplateMapper {

  QuizTemplate toQuizTemplate(QuizTemplateDto quizTemplateDto);
}
