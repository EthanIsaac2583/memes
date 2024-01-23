package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.service.exception.DataConflictException;
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
  void findByUsername_shouldReturnLead_whenExistingLeadUsernamePassed() {
    Lead expected = dataGenerator.generateLead();
    doReturn(Optional.of(expected)).when(leadRepository).findByUsername(expected.getUsername());

    Lead actual = leadService.findByUsername(expected.getUsername());

    assertEquals(expected, actual);
    verify(leadRepository).findByUsername(expected.getUsername());
  }

  @Test
  void save_shouldThrowDataConflictException_whenLeadAlreadyExists() {
    Lead existingLead = dataGenerator.generateLead();
    Lead expected = generateLeadWithUsername(existingLead);
    doReturn(Optional.of(existingLead)).when(leadRepository).findByUsername(expected.getUsername());

    DataConflictException thrown = assertThrows(DataConflictException.class, () -> leadService.save(expected));

    String expectedMessage = "Lead already exists";
    assertEquals(expectedMessage, thrown.getMessage());

    verify(leadRepository).findByUsername(expected.getUsername());
  }

  @Test
  void save_shouldAllowUpdate_whenSameLeadPassed() {
    Lead expected = dataGenerator.generateLead();
    doReturn(Optional.of(expected)).when(leadRepository).findByUsername(expected.getUsername());
    doReturn(expected).when(leadRepository).save(expected);

    Lead actual = leadService.save(expected);

    assertEquals(expected, actual);
    verify(leadRepository).save(expected);
  }

  private Lead generateLeadWithUsername(Lead existingLead) {
    Lead lead = dataGenerator.generateLead(null);
    lead.setUsername(existingLead.getUsername());

    return lead;
  }
}
