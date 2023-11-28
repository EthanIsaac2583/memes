package kz.ruanjian.memed.mapper;

import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.model.Template;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

  Template toTemplate(TemplateDto templateDto);
}
