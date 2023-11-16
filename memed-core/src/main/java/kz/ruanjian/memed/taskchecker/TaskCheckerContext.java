package kz.ruanjian.memed.taskchecker;

import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.pojo.BlankType;
import org.springframework.stereotype.Component;

@Component
public class TaskCheckerContext implements TaskChecker {

  private final SingleChoiceTaskChecker singleChoiceTaskChecker;

  public TaskCheckerContext(SingleChoiceTaskChecker singleChoiceTaskChecker) {
    this.singleChoiceTaskChecker = singleChoiceTaskChecker;
  }

  @Override
  public int check(Task task) {
    if (BlankType.SINGLE_CHOICE.equals(task.getBlank().getType())) {
      return singleChoiceTaskChecker.check(task);
    }

    throw new TaskCheckException(String.format("There is no task checker for %s type", task.getBlank().getType()));
  }
}
