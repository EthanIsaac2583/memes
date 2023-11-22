import {TQuestion} from "../model/question";
import {TBlank} from "../model/blank";

export type TTaskDto = {
  id?: number;
  name: string;
  question: TQuestion;
  blank: TBlank;
  answer: string;
}
