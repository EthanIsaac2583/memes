package kz.ruanjian.memed.config;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.spring.DBRiderTestExecutionListener;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners(value = {PrepareDatabaseTestExecutionListener.class, DBRiderTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public abstract class AbstractComponentsTest {

  protected static final Network network = Network.newNetwork();

  protected static final PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:14.8")
    .withExposedPorts(5432)
    .withDatabaseName("test")
    .withUsername("test")
    .withPassword("test")
    .withNetwork(network)
    .withNetworkAliases("db");

  protected static final GenericContainer<?> app = new GenericContainer<>("memed-core:latest")
    .dependsOn(db)
    .withExposedPorts(8080)
    .withEnv("DB_URL", "jdbc:postgresql://db/test")
    .withEnv("DB_USERNAME", "test")
    .withEnv("DB_PASSWORD", "test")
    .withEnv("SECURITY_SECRET", "A1B2C3D4E5F6A7B8C9D0E1F2A3B4C5D6E7F8A9B0C1D2E3F4A5B6C7D8E9F0")
    .withNetwork(network)
    .withFileSystemBind("./logs", "/workspace/logs");

  @DynamicPropertySource
  protected static void setupProperties(DynamicPropertyRegistry registry) {
    Startables.deepStart(app).join();

    registry.add("spring.datasource.driver-class-name", db::getDriverClassName);
    registry.add("spring.datasource.url", db::getJdbcUrl);
    registry.add("spring.datasource.username", db::getUsername);
    registry.add("spring.datasource.password", db::getPassword);
  }

  protected String getServiceUrl(String urlSuffix) {
    return "http://localhost:" + app.getMappedPort(8080) + urlSuffix;
  }
}
