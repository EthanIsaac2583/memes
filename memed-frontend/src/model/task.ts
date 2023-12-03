import {Body} from "./body";
import {Blank} from "./blank";
import {Answer} from "./answer";

export type TTask = {
  id: number;
  name: string;
  body: Body;
  blank: Blank;
  answer: Answer;
};
