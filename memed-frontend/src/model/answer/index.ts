import {EBlankType} from "../blank-type";

type TSingleChoiceAnswer = {
  type: EBlankType.SINGLE_CHOICE;
  key: string;
}

export type TAnswer = TSingleChoiceAnswer;
