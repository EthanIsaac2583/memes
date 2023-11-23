import {TQuestion} from "../model/question";
import {TBlank} from "../model/blank";
import {TAnswer} from "../model/answer";

export type TTaskDto = {
  id?: number;
  name: string;
  question: TQuestion;
  blank: TBlank;
  answer: TAnswer;
};
