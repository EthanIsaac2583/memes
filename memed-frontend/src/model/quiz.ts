import {QuizStatus} from "./quiz-status";
import {TQuizTemplate} from "./quiz-template";

export type Quiz = {
  id: number;
  status: QuizStatus;
  grade: number;
  template: TQuizTemplate;
};
