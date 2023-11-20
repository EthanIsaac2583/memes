package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.LeadQuizAnswerGradeDto;
import kz.ruanjian.memed.dto.LeadQuizAnswerProvideDto;
import kz.ruanjian.memed.model.LeadQuizAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadQuizAnswerMapper {

  void applyAnswer(@MappingTarget LeadQuizAnswer answer, LeadQuizAnswerProvideDto provideDto);

  void applyGrade(@MappingTarget LeadQuizAnswer answer, LeadQuizAnswerGradeDto gradeDto);
}
