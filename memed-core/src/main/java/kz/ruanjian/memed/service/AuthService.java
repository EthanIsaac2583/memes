package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.security.SecurityManager;
import kz.ruanjian.memed.service.exception.AuthenticationFailedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

  private final LeadService leadService;
  private final VisitService visitService;
  private final PasswordEncoder passwordEncoder;
  private final SecurityManager securityManager;
  private final AuthenticationManager authenticationManager;

  public AuthService(LeadService leadService,
                     VisitService visitService,
                     PasswordEncoder passwordEncoder,
                     SecurityManager securityManager,
                     AuthenticationManager authenticationManager) {
    this.leadService = leadService;
    this.visitService = visitService;
    this.passwordEncoder = passwordEncoder;
    this.securityManager = securityManager;
    this.authenticationManager = authenticationManager;
  }

  public AuthResponseDto login(AuthDto authDto) {
    authenticate(authDto);
    Lead lead = leadService.findByUsername(authDto.getUsername());

    return generateAuthResponse(lead);
  }

  @Transactional
  public AuthResponseDto register(AuthDto authDto) {
    Visit visit = visitService.create();
    Lead lead = generateLead(authDto, visit);
    leadService.save(lead);

    return generateAuthResponse(lead);
  }

  private void authenticate(AuthDto authDto) {
    try {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
      authenticationManager.authenticate(token);
    } catch (AuthenticationException authenticationException) {
      throw new AuthenticationFailedException(authenticationException.getMessage());
    }
  }

  private AuthResponseDto generateAuthResponse(Lead lead) {
    String token = securityManager.generateToken(lead);
    AuthResponseDto authResponseDto = new AuthResponseDto();
    authResponseDto.setToken(token);
    authResponseDto.setLead(lead);

    return authResponseDto;
  }

  private Lead generateLead(AuthDto authDto, Visit visit) {
    return Lead.builder()
      .visit(visit)
      .username(authDto.getUsername())
      .password(passwordEncoder.encode(authDto.getPassword()))
      .build();
  }
}
