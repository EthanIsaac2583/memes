package kz.ruanjian.memed;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.respository.LeadRepository;
import kz.ruanjian.memed.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AdminInitializer  implements ApplicationListener<ApplicationReadyEvent> {

  private static Logger log = LoggerFactory.getLogger(AdminInitializer.class);

  private LeadRepository leadRepository;
  private VisitService visitService;
  private PasswordEncoder passwordEncoder;

  public AdminInitializer(LeadRepository leadRepository,
                          VisitService visitService,
                          PasswordEncoder passwordEncoder) {
    this.leadRepository = leadRepository;
    this.visitService = visitService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
    String adminUsername = "admin";
    Optional<Lead> persistedAdmin = leadRepository.findByUsername(adminUsername);

    if (persistedAdmin.isEmpty()) {
      createAdmin(adminUsername, "password");
    } else {
      if (log.isInfoEnabled()) {
        log.info("Default admin already exists");
      }
    }
  }

  private Lead createAdmin(String username, String password) {
    Visit visit = visitService.create();
    Lead lead = Lead.builder()
      .username(username)
      .password(passwordEncoder.encode(password))
      .role("ROLE_ADMIN")
      .visit(visit)
      .build();

    leadRepository.save(lead);

    if (log.isInfoEnabled()) {
      log.info("Default admin created");
    }

    return lead;
  }
}
