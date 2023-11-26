package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.QuizTemplateDto;
import kz.ruanjian.memed.model.Template;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizTemplateMapper {

  Template toQuizTemplate(QuizTemplateDto quizTemplateDto);
}
