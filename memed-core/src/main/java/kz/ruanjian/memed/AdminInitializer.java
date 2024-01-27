package kz.ruanjian.memed;

import kz.ruanjian.memed.config.MemedProperties;
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

  private final LeadRepository leadRepository;
  private final VisitService visitService;
  private final PasswordEncoder passwordEncoder;
  private final MemedProperties memedProperties;

  public AdminInitializer(LeadRepository leadRepository,
                          VisitService visitService,
                          PasswordEncoder passwordEncoder,
                          MemedProperties memedProperties) {
    this.leadRepository = leadRepository;
    this.visitService = visitService;
    this.passwordEncoder = passwordEncoder;
    this.memedProperties = memedProperties;
  }

  @Override
  @Transactional
  public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
    Optional<Lead> persistedAdmin = leadRepository.findByUsername(memedProperties.getSecurity().getAdminUsername());

    if (persistedAdmin.isEmpty()) {
      createAdmin();
    } else {
      if (log.isInfoEnabled()) {
        log.info("Default admin already exists");
      }
    }
  }

  private Lead createAdmin() {
    Visit visit = visitService.create();
    Lead lead = Lead.builder()
      .username(memedProperties.getSecurity().getAdminUsername())
      .password(passwordEncoder.encode(memedProperties.getSecurity().getAdminPassword()))
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
