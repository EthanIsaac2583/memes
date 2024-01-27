package kz.ruanjian.memed;

import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.service.LeadService;
import kz.ruanjian.memed.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminInitializer  implements ApplicationListener<ApplicationReadyEvent> {

  private static Logger log = LoggerFactory.getLogger(AdminInitializer.class);

  private LeadService leadService;
  private VisitService visitService;
  private PasswordEncoder passwordEncoder;

  public AdminInitializer(LeadService leadService, VisitService visitService, PasswordEncoder passwordEncoder) {
    this.leadService = leadService;
    this.visitService = visitService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    String adminUsername = "admin";
    Lead existingAdmin = leadService.findByUsername(adminUsername);

    if (existingAdmin == null) {
      createAdmin(adminUsername, "password");

      if (log.isInfoEnabled()) {
        log.info("Default admin created");
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
    return leadService.save(lead);
  }
}
