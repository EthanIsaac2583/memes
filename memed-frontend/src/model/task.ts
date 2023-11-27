import {Body} from "./body";
import {TBlank} from "./blank";
import {TAnswer} from "./answer";

export type TTask = {
  id: number;
  name: string;
  question: Body;
  blank: TBlank;
  answer: TAnswer;
};
