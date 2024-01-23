package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.SecurityManager;
import kz.ruanjian.memed.service.exception.AuthenticationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

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
  void login_shouldThrowAuthenticationFailedException_whenAnyOfAuthenticationExceptionOccurs() {
    AuthDto authDto = dataGenerator.generateAuthDto();
    Authentication authentication = generateAuthentication(authDto);
    doThrow(BadCredentialsException.class).when(authenticationManager).authenticate(authentication);

    assertThrows(AuthenticationFailedException.class, () -> authService.login(authDto));
  }

  @Test
  void login_should_when2() {
  }

  @Test
  void login_should_when3() {
  }

  @Test
  void register() {
  }

  private Authentication generateAuthentication(AuthDto authDto) {
    return new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
  }
}
