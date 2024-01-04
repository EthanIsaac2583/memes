import {BlankType} from "./blank-type";

export type SingleChoiceAnswer = {
  type: BlankType.SINGLE_CHOICE;
  key: string;
}

export type MultipleChoiceAnswer = {
  type: BlankType.MULTIPLE_CHOICE;
  keys: Array<string>;
}

export type Answer = SingleChoiceAnswer | MultipleChoiceAnswer;
