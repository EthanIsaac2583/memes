package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.LeadQuizAnswerGradeDto;
import kz.ruanjian.memed.dto.LeadQuizAnswerProvideDto;
import kz.ruanjian.memed.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadQuizAnswerMapper {

  void applyAnswer(@MappingTarget Question answer, LeadQuizAnswerProvideDto provideDto);

  void applyGrade(@MappingTarget Question answer, LeadQuizAnswerGradeDto gradeDto);
}
