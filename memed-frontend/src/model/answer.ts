import {BlankType} from "./blank-type";

type TSingleChoiceAnswer = {
  type: BlankType.SINGLE_CHOICE;
  key: string;
}

export type TAnswer = TSingleChoiceAnswer;
