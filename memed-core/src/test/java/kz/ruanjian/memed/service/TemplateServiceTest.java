package kz.ruanjian.memed.service;

import kz.ruanjian.memed.mapper.TemplateMapper;
import kz.ruanjian.memed.mapper.TemplateMapperImpl;
import kz.ruanjian.memed.respository.TemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TemplateServiceTest {

  @Mock
  TemplateRepository templateRepository;

  @Spy
  TemplateMapper templateMapper = new TemplateMapperImpl();

  @Mock
  TaskService taskService;

  @Test
  void findById() {
  }

  @Test
  void findAll() {
  }

  @Test
  void save() {
  }
}
