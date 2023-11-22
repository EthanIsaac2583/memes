import {TPlainTextQuestion} from "../model/question/plain-text-question";
import {TSingleChoiceBlank} from "../model/blank/single-choice-blank";

export type TTaskDto = {
  id?: number;
  name: string;
  question: TPlainTextQuestion;
  blank: TSingleChoiceBlank;
  answer: string;
}
