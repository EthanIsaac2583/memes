import {TPlainTextQuestion} from "../model/question/plain-text-question";

export type TTaskDto = {
  id?: number;
  name: string;
  question: TPlainTextQuestion;
  blank: string;
  answer: string;
}
