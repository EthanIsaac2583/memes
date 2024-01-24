package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.mapper.TemplateMapper;
import kz.ruanjian.memed.mapper.TemplateMapperImpl;
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

import java.util.Optional;

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
  void findAll() {
  }

  @Test
  void save() {
  }
}
