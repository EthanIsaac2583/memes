package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.VisitDto;
import kz.ruanjian.memed.model.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper {

  Visit toVisit(VisitDto visitDto);
}
