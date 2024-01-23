package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.SecurityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

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
  void findLeadByUsername() {
  }

  @Test
  void login() {
  }

  @Test
  void register() {
  }
}
