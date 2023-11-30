package kz.ruanjian.memed.dto;

import java.util.Objects;

public class RequestQuizDto {

  private Long templateId;

  public RequestQuizDto() {
  }

  public Long getTemplateId() {
    return templateId;
  }

  public void setTemplateId(Long templateId) {
    this.templateId = templateId;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    RequestQuizDto that = (RequestQuizDto) o;
    return Objects.equals(templateId, that.templateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateId);
  }

  @Override
  public String toString() {
    return "RequestQuizDto{" +
      "templateId=" + templateId +
      '}';
  }
}
