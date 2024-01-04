import {Answer} from "../../model/answer";
import {Task} from "../../model/task";

export interface TaskRenderer {
  task: Task;
  onSubmitBlank?: (answer: Answer) => void;
}
