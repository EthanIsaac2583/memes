package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.SecurityManager;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  LeadRepository leadRepository;

  @Mock
  VisitRepository visitRepository;

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  SecurityManager securityManager;

  @Mock
  AuthenticationManager authenticationManager;

  @InjectMocks
  AuthService authService;

  DataGenerator dataGenerator;

  @BeforeEach
  void setUp() {
    dataGenerator = new DataGenerator();
  }

  @Test
  void findLeadByUsername_shouldThrowNotFoundException_whenNotExistingUserCredentialsPassed() {
    AuthDto authDto = dataGenerator.generateAuthDto();
    doReturn(Optional.empty()).when(leadRepository).findByUsername(authDto.getUsername());

    NotFoundException thrown = assertThrows(NotFoundException.class, () -> authService.login(authDto));

    String exceptedMessage = "User not found";
    assertEquals(exceptedMessage, thrown.getMessage());

    verify(leadRepository).findByUsername(authDto.getUsername());
  }

  @Test
  void login() {
  }

  @Test
  void register() {
  }
}
