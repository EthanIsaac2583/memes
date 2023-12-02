package kz.ruanjian.memed.util.grader;

import kz.ruanjian.memed.model.Question;

public interface Grader {

  int grade(Question question);
}
