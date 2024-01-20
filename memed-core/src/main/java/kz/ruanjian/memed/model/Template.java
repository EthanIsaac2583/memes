package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "templates")
public class Template {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @Column(name = "quiz_limit")
  private int limit;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "templates_tasks",
    joinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
  private Set<Task> tasks;

  public Template() {
  }

  private Template(Builder builder) {
    id = builder.id;
    name = builder.name;
    description = builder.description;
    limit = builder.limit;
    tasks = builder.tasks;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Template template = (Template) o;
    return limit==template.limit && Objects.equals(id, template.id) && Objects.equals(name, template.name) && Objects.equals(description, template.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, limit);
  }

  @Override
  public String toString() {
    return "Template{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", limit=" + limit +
      '}';
  }

  public static final class Builder {

    private Long id;
    private String name;
    private String description;
    private int limit;
    private Set<Task> tasks;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder limit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder tasks(Set<Task> tasks) {
      this.tasks = tasks;
      return this;
    }

    public Template build() {
      return new Template(this);
    }
  }
}
