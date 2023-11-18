package kz.ruanjian.memed.model;

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
@Table(name = "quiz_templates")
public class QuizTemplate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(name = "quiz_templates_tasks",
    joinColumns = @JoinColumn(name = "quiz_template_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
  private Set<Task> tasks;

  public QuizTemplate() {
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
    QuizTemplate that = (QuizTemplate) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(tasks, that.tasks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, tasks);
  }

  @Override
  public String toString() {
    return "QuizTemplate{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", tasks=" + tasks +
      '}';
  }
}
