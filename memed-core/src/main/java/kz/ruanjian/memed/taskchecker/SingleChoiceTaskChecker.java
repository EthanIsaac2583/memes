package kz.ruanjian.memed.taskchecker;

import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.pojo.blank.SingleChoiceBlank;
import org.springframework.stereotype.Component;

@Component
public class SingleChoiceTaskChecker implements TaskChecker {

  @Override
  public int check(Task task) {
    if (task.getBlank() instanceof SingleChoiceBlank blank) {
      return processTask(blank);
    }

    throw new TaskCheckException("");
  }

  private int processTask(SingleChoiceBlank blank) {
    System.out.println("--------> single choice " + blank);
    return 100;
  }
}
