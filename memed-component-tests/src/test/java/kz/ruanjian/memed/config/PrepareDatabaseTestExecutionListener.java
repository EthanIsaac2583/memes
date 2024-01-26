package kz.ruanjian.memed.config;

import org.flywaydb.core.Flyway;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class PrepareDatabaseTestExecutionListener implements TestExecutionListener {

  private static final String DB_URL_PROPERTY = "spring.datasource.url";
  private static final String DB_USERNAME_PROPERTY = "spring.datasource.username";
  private static final String DB_PASSWORD_PROPERTY = "spring.datasource.password";

  @Override
  public void beforeTestMethod(TestContext testContext) throws Exception {
    Environment environment = testContext.getApplicationContext().getEnvironment();

    String url = environment.getProperty(DB_URL_PROPERTY);
    String username = environment.getProperty(DB_USERNAME_PROPERTY);
    String password = environment.getProperty(DB_PASSWORD_PROPERTY);

    Flyway flyway = Flyway.configure()
      .dataSource(url, username, password)
      .cleanDisabled(false)
      .load();

    flyway.clean();
    flyway.migrate();

    TestExecutionListener.super.beforeTestMethod(testContext);
  }
}
