package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitServiceTest {

  @Mock
  VisitRepository visitRepository;

  @InjectMocks
  VisitService visitService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findById_shouldThrowNotFoundException_whenNotExistingVisitRequested() {
    UUID id = dataGenerator.generateUUID();
    doReturn(Optional.empty()).when(visitRepository).findById(id);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> visitService.findById(id));

    String expectedMessage = "Visit not found";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(visitRepository).findById(id);
  }

  @Test
  void findById_shouldReturnVisit_whenExistingVisitRequested() {
    Visit expected = dataGenerator.generateVisit();
    doReturn(Optional.of(expected)).when(visitRepository).findById(expected.getId());

    Visit actual = visitService.findById(expected.getId());

    assertEquals(expected, actual);
    verify(visitRepository).findById(expected.getId());
  }

  @Test
  void create_shouldCreateVisit_whenRequested() {
    Visit expected = dataGenerator.generateVisit();
    doReturn(expected).when(visitRepository).save(any(Visit.class));

    Visit actual = visitService.create();

    assertEquals(expected, actual);
    verify(visitRepository).save(any(Visit.class));
  }
}
