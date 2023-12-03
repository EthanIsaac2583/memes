import {Body} from "../model/body";
import {Blank} from "../model/blank";
import {Answer} from "../model/answer";

export type TTaskDto = {
  id?: number;
  name: string;
  body: Body;
  blank: Blank;
  answer: Answer;
};
