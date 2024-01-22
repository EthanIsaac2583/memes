package kz.ruanjian.memed.controller;

import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.dto.LoginDto;
import kz.ruanjian.memed.dto.RegisterDto;
import kz.ruanjian.memed.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public AuthResponseDto register(@RequestBody RegisterDto registerDto) {
    return authService.register(registerDto);
  }

  @PostMapping("/login")
  public AuthResponseDto login(@RequestBody LoginDto loginDto) {
    return authService.login(loginDto);
  }
}
