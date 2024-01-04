import {Task} from "./task";
import {Answer} from "./answer";

export type Question = {
  id: number;
  task: Task;
  assessed: boolean;
  grade: number;
  answer: Answer | null;
}
