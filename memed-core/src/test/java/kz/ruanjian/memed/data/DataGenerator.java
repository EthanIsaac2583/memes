package kz.ruanjian.memed.data;

import com.github.javafaker.Faker;
import kz.ruanjian.memed.dto.AuthDto;
import kz.ruanjian.memed.model.Lead;
import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Visit;
import kz.ruanjian.memed.pojo.BlankType;
import kz.ruanjian.memed.pojo.BodyType;
import kz.ruanjian.memed.pojo.Option;
import kz.ruanjian.memed.pojo.answer.SingleChoiceAnswer;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import kz.ruanjian.memed.pojo.body.ImageBody;
import kz.ruanjian.memed.pojo.body.PlainTextBody;
import kz.ruanjian.memed.pojo.body.YoutubeVideoBody;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGenerator {

  private static final Long ID_MIN = 1L;
  private static final Long ID_MAX = 10_000_000L;

  private final Faker faker = new Faker();

  public UUID generateUUID() {
    return UUID.randomUUID();
  }

  public Long generateLongId() {
    return faker.number().numberBetween(ID_MIN, ID_MAX);
  }

  public String generateWord(int size) {
    return faker.lorem()
      .fixedString(size)
      .replaceAll(" ", "_");
  }

  public String generateSentence(int words) {
    return faker.lorem()
      .sentence(words);
  }

  public SingleChoiceAnswer generateSingleChoiceAnswer() {
    return SingleChoiceAnswer.builder()
      .type(BlankType.SINGLE_CHOICE)
      .key(generateWord(5))
      .build();
  }

  public SingleChoiceBlank generateSingleChoiceBlank() {
    return SingleChoiceBlank.builder()
      .type(BlankType.SINGLE_CHOICE)
      .options(generateOptions(3))
      .build();
  }

  public PlainTextBody generatePlainTextBody() {
    return PlainTextBody.builder()
      .type(BodyType.PLAIN_TEXT)
      .text(generateSentence(3))
      .build();
  }

  public ImageBody generateImageBody() {
    return ImageBody.builder()
      .type(BodyType.IMAGE)
      .text(generateSentence(4))
      .url(generateUrl())
      .build();
  }

  public YoutubeVideoBody generateYoutubeVideoBody() {
    return YoutubeVideoBody.builder()
      .type(BodyType.YOUTUBE_VIDEO)
      .text(generateSentence(5))
      .markup(generateSentence(100))
      .build();
  }

  public Visit generateVisit() {
    return Visit.builder()
      .id(generateUUID())
      .createdAt(ZonedDateTime.now())
      .build();
  }

  public Task generateTask() {
    return Task.builder()
      .id(generateLongId())
      .name(generateWord(5))
      .body(generatePlainTextBody())
      .blank(generateSingleChoiceBlank())
      .answer(generateSingleChoiceAnswer())
      .isDeleted(false)
      .build();
  }

  public List<Task> generateTasks(int size) {
    return Stream.generate(this::generateTask)
      .limit(size)
      .collect(Collectors.toList());
  }

  public Template generateTemplate() {
    return Template.builder()
      .id(generateLongId())
      .name(generateSentence(3))
      .description(generateSentence(10))
      .limit(1)
      .tasks(new HashSet<>(generateTasks(2)))
      .build();
  }

  public List<Template> generateTemplates(int size) {
    return Stream.generate(this::generateTemplate)
      .limit(size)
      .collect(Collectors.toList());
  }

  public Lead generateLead() {
    return generateLead(generateLongId());
  }

  public Lead generateLead(Long id) {
    return Lead.builder()
      .id(id)
      .username(faker.name().username())
      .password(faker.internet().password())
      .visit(generateVisit())
      .build();
  }

  public AuthDto generateAuthDto() {
    return AuthDto.builder()
      .username(faker.name().username())
      .password(faker.internet().password())
      .build();
  }

  public AuthDto generateAuthDto(Lead lead) {
    return AuthDto.builder()
      .username(lead.getUsername())
      .password(lead.getPassword())
      .build();
  }

  private Option generateOption() {
    Option option = new Option();

    option.setKey(generateWord(4));
    option.setValue(generateWord(10));

    return option;
  }

  private Set<Option> generateOptions(int size) {
    return Stream.generate(this::generateOption)
      .limit(size)
      .collect(Collectors.toSet());
  }

  private String generateUrl() {
    return faker.internet()
      .url();
  }
}
