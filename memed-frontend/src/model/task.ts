import {Body} from "./body";
import {Blank} from "./blank";
import {Answer} from "./answer";

export type Task = {
  id: number;
  name: string;
  body: Body;
  blank: Blank;
  answer: Answer;
};
