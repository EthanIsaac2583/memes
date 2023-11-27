import {Body} from "../model/body";
import {TBlank} from "../model/blank";
import {TAnswer} from "../model/answer";

export type TTaskDto = {
  id?: number;
  name: string;
  body: Body;
  blank: TBlank;
  answer: TAnswer;
};
