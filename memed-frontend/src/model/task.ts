import {TQuestion} from "./question";
import {TBlank} from "./blank";
import {TAnswer} from "./answer";

export type TTask = {
  id: number;
  name: string;
  question: TQuestion;
  blank: TBlank;
  answer: TAnswer;
};
