import {Answer} from "../../model/answer";
import {Question} from "../../model/question";

export interface BlankRenderer {
  question: Question;
  onSubmitBlank?: (answer: Answer) => void;
}
