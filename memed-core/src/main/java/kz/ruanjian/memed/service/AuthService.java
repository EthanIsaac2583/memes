package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.dto.LoginDto;
import kz.ruanjian.memed.dto.RegisterDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.JwtManager;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthService {

  private final LeadRepository leadRepository;
  private final VisitRepository visitRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtManager jwtManager;
  private final AuthenticationManager authenticationManager;

  public AuthService(LeadRepository leadRepository,
                     VisitRepository visitRepository,
                     PasswordEncoder passwordEncoder,
                     JwtManager jwtManager,
                     AuthenticationManager authenticationManager) {
    this.leadRepository = leadRepository;
    this.visitRepository = visitRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtManager = jwtManager;
    this.authenticationManager = authenticationManager;
  }

  @Transactional
  public AuthDto register(RegisterDto registerDto) {
    Visit visit = createVisit();
    Lead lead = generateLead(registerDto, visit);
    leadRepository.save(lead);

    String token = jwtManager.generateToken(lead);

    AuthDto authDto = new AuthDto();
    authDto.setToken(token);

    return authDto;
  }

  @Transactional
  public AuthDto login(LoginDto loginDto) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    Lead lead = findLeadByUsername(loginDto.getUsername());
    String token = jwtManager.generateToken(lead);

    AuthDto authDto = new AuthDto();
    authDto.setToken(token);
    return authDto;
  }

  private Lead findLeadByUsername(String username) {
    return leadRepository
      .findByUsername(username)
      .orElseThrow(() -> new NotFoundException("Lead not found"));
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
