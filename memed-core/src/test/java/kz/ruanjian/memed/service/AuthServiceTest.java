package kz.ruanjian.memed.service;

import kz.ruanjian.memed.data.DataGenerator;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  LeadService leadService;

  @Mock
  VisitService visitService;

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
  void login_shouldReturnAuthResponse_whenSuccessfullyLoggedIn() {
    Lead lead = dataGenerator.generateLead();
    AuthDto authDto = dataGenerator.generateAuthDto(lead);
    doReturn(lead).when(leadService).findByUsername(authDto.getUsername());

    String token = dataGenerator.generateWord(100);
    doReturn(token).when(securityManager).generateToken(lead);

    AuthResponseDto expected = AuthResponseDto.builder()
      .token(token)
      .lead(lead)
      .build();

    AuthResponseDto actual = authService.login(authDto);

    assertEquals(expected, actual);
  }

  @Test
  void register_shouldRegisterUser_whenValidCredentialsPassed() {
    AuthDto authDto = dataGenerator.generateAuthDto();
    Visit visit = dataGenerator.generateVisit();
    doReturn(visit).when(visitService).create();

    String encodedPassword = dataGenerator.generateWord(40);
    doReturn(encodedPassword).when(passwordEncoder).encode(authDto.getPassword());

    Lead lead = Lead.builder()
      .visit(visit)
      .username(authDto.getUsername())
      .password(encodedPassword)
      .role("ROLE_USER")
      .build();

    String token = dataGenerator.generateWord(150);
    doReturn(token).when(securityManager).generateToken(lead);

    AuthResponseDto expected = AuthResponseDto.builder()
      .lead(lead)
      .token(token)
      .build();

    AuthResponseDto actual = authService.register(authDto);

    assertEquals(expected, actual);
  }

  private Authentication generateAuthentication(AuthDto authDto) {
    return new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
  }
}
