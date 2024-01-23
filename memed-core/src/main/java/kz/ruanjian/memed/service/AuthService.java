package kz.ruanjian.memed.service;

import kz.ruanjian.memed.dto.AuthResponseDto;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.respository.VisitRepository;
import kz.ruanjian.memed.security.SecurityManager;
import kz.ruanjian.memed.service.exception.DataConflictException;
import kz.ruanjian.memed.service.exception.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AuthService {

  private static final String USERNAME_EXISTS = "Username already exists";

  private static final String NOT_FOUND = "Lead not found";

  private final LeadRepository leadRepository;
  private final VisitRepository visitRepository;
  private final PasswordEncoder passwordEncoder;
  private final SecurityManager securityManager;
  private final AuthenticationManager authenticationManager;

  public AuthService(LeadRepository leadRepository,
                     VisitRepository visitRepository,
                     PasswordEncoder passwordEncoder,
                     SecurityManager securityManager,
                     AuthenticationManager authenticationManager) {
    this.leadRepository = leadRepository;
    this.visitRepository = visitRepository;
    this.passwordEncoder = passwordEncoder;
    this.securityManager = securityManager;
    this.authenticationManager = authenticationManager;
  }

  public Lead findLeadByUsername(String username) {
    return leadRepository
      .findByUsername(username)
      .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  public AuthResponseDto login(AuthDto authDto) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
    Lead lead = findLeadByUsername(authDto.getUsername());

    return generateAuthResponse(lead);
  }

  @Transactional
  public AuthResponseDto register(AuthDto authDto) {
    Visit visit = createVisit();
    Lead lead = generateLead(authDto, visit);
    verifyUsernameIsUnique(lead);
    leadRepository.save(lead);

    return generateAuthResponse(lead);
  }

  private Visit createVisit() {
    Visit visit = new Visit();
    visit.setCreatedAt(ZonedDateTime.now());

    return visitRepository.save(visit);
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

  private void verifyUsernameIsUnique(Lead lead) {
    Optional<Lead> persistedLead = leadRepository.findByUsername(lead.getUsername());

    if (persistedLead.isPresent()) {
      throw new DataConflictException(USERNAME_EXISTS);
    }
  }
}
