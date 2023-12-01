import {Body} from "./body";
import {TBlank} from "./blank";
import {Answer} from "./answer";

export type TTask = {
  id: number;
  name: string;
  body: Body;
  blank: TBlank;
  answer: Answer;
};
