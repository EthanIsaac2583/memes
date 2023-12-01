import {Body} from "../model/body";
import {TBlank} from "../model/blank";
import {Answer} from "../model/answer";

export type TTaskDto = {
  id?: number;
  name: string;
  body: Body;
  blank: TBlank;
  answer: Answer;
};
