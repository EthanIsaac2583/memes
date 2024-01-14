package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.LoginDto;
import kz.ruanjian.memed.dto.RegisterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

  @Transactional
  public AuthDto register(RegisterDto registerDto) {
    return new AuthDto();
  }

  public AuthDto login(LoginDto loginDto) {
    return new AuthDto();
  }
}
