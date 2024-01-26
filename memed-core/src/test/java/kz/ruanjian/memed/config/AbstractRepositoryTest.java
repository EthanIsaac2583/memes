package kz.ruanjian.memed.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ruanjian.memed.pojo.coverter.AnswerConverter;
import kz.ruanjian.memed.pojo.json.PojoJson;
import kz.ruanjian.memed.util.json.JsonUtil;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;

@DataJpaTest
@Import(value = {ObjectMapper.class, JsonUtil.class, PojoJson.class, AnswerConverter.class})
@TestExecutionListeners(value = {PrepareDbTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public abstract class AbstractRepositoryTest {
}
