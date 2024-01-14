package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.LoginDto;
import kz.ruanjian.memed.dto.RegisterDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthService {

  private LeadRepository leadRepository;
  private VisitRepository visitRepository;
  private PasswordEncoder passwordEncoder;
  private JwtService jwtService;

  public AuthService(LeadRepository leadRepository,
                     VisitRepository visitRepository,
                     PasswordEncoder passwordEncoder,
                     JwtService jwtService) {
    this.leadRepository = leadRepository;
    this.visitRepository = visitRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Transactional
  public AuthDto register(RegisterDto registerDto) {
    Visit visit = createVisit();
    Lead lead = generateLead(registerDto, visit);
    leadRepository.save(lead);

    String token = jwtService.generateToken(lead);

    AuthDto authDto = new AuthDto();
    authDto.setToken(token);

    return authDto;
  }

  public AuthDto login(LoginDto loginDto) {
    return new AuthDto();
  }

  private Visit createVisit() {
    Visit visit = new Visit();
    visit.setCreatedAt(ZonedDateTime.now());

    return visitRepository.save(visit);
  }

  private Lead generateLead(RegisterDto registerDto, Visit visit) {
    Lead lead = new Lead();

    lead.setUsername(registerDto.getUsername());
    lead.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    lead.setVisit(visit);

    return lead;
  }
}
