package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.TemplateDto;
import kz.ruanjian.memed.mapper.TemplateMapper;
import kz.ruanjian.memed.mapper.TemplateMapperImpl;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.TemplateRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TemplateServiceTest {

  @Mock
  TemplateRepository templateRepository;

  @Spy
  TemplateMapper templateMapper = new TemplateMapperImpl();

  @Mock
  TaskService taskService;

  @InjectMocks
  TemplateService templateService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findById_shouldThrowNotFoundException_whenNotExistingTemplateIdPassed() {
    Long id = dataGenerator.generateLongId();
    doReturn(Optional.empty()).when(templateRepository).findById(id);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> templateService.findById(id));

    String expectedMessage = "Template not found";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(templateRepository).findById(id);
  }

  @Test
  void findById_shouldReturnTemplate_whenExistingTemplateIdPassed() {
    Template expected = dataGenerator.generateTemplate();
    doReturn(Optional.of(expected)).when(templateRepository).findById(expected.getId());

    Template actual = templateService.findById(expected.getId());

    assertEquals(expected, actual);
    verify(templateRepository).findById(expected.getId());
  }

  @Test
  void findAll_shouldReturnEmptyPage_when1() {
    Pageable pageable = PageRequest.of(1000, 10);
    Page<Template> expected = new PageImpl<>(new ArrayList<>(), pageable, 400);
    doReturn(expected).when(templateRepository).findAll(pageable);

    Page<Template> actual = templateService.findAll(pageable);

    assertEquals(expected, actual);
    verify(templateRepository).findAll(pageable);
  }

  @Test
  void findAll_shouldReturnPage_when2() {
    Pageable pageable = PageRequest.of(1, 20);
    Page<Template> expected = new PageImpl<>(dataGenerator.generateTemplates(20), pageable, 300);
    doReturn(expected).when(templateRepository).findAll(pageable);

    Page<Template> actual = templateService.findAll(pageable);

    assertEquals(expected, actual);
    verify(templateRepository).findAll(pageable);
  }

  @Test
  void save_shouldSaveTemplate_whenValidTemplateDtoPassed() {
    Template expected = dataGenerator.generateTemplate();
    TemplateDto templateDto = generateTemplateDto(expected);
    doReturn(expected.getTasks()).when(taskService).findAllById(templateDto.getTaskIds());
    doReturn(expected).when(templateRepository).save(expected);

    Template actual = templateService.save(templateDto);

    assertEquals(expected, actual);
    verify(templateRepository).save(expected);
  }

  private TemplateDto generateTemplateDto(Template template) {
    TemplateDto templateDto = new TemplateDto();

    templateDto.setId(template.getId());
    templateDto.setName(template.getName());
    templateDto.setDescription(template.getDescription());
    templateDto.setTaskIds(getTaskIds(template.getTasks()));
    templateDto.setLimit(template.getLimit());

    return templateDto;
  }

  private Set<Long> getTaskIds(Set<Task> tasks) {
    return tasks.stream()
      .map(Task::getId)
      .collect(Collectors.toSet());
  }
}
