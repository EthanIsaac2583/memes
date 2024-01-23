package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.SecurityManager;
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

  @Test
  void findLeadByUsername() {
  }

  @Test
  void login() {
    AuthDto authDto = generateAuthDto();

    AuthResponseDto actual = authService.login(authDto);

    System.out.println("-----> " + actual);
  }

  @Test
  void register() {
  }

  private AuthDto generateAuthDto() {
    AuthDto authDto = new AuthDto();

    authDto.setUsername("username");
    authDto.setPassword("password");

    return authDto;
  }
}
