package kz.ruanjian.memed;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import kz.ruanjian.memed.config.AbstractComponentTest;
import org.junit.jupiter.api.Test;

@DataSet(value = "dataset/template/initial.yml", strategy = SeedStrategy.INSERT)
class TemplateComponentTest extends AbstractComponentTest {

  @Test
  void test1() {
  }
}
