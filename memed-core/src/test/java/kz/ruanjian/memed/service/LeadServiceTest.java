package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LeadServiceTest {

  @Mock
  LeadRepository leadRepository;

  @InjectMocks
  LeadService leadService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findByUsername_shouldThrowNotFoundException_whenNotExistingLeadUsernamePassed() {
    String username = dataGenerator.generateWord(8);
    doReturn(Optional.empty()).when(leadRepository).findByUsername(username);

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> leadService.findByUsername(username));

    String expectedMessage = "Lead not found";
    assertEquals(expectedMessage, thrown.getLocalizedMessage());

    verify(leadRepository).findByUsername(username);
  }

  @Test
  void findByUsername_should_when2() {
  }
}
