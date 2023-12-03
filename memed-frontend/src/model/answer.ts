import {BlankType} from "./blank-type";

type SingleChoiceAnswer = {
  type: BlankType.SINGLE_CHOICE;
  key: string;
}

type MultipleChoiceAnswer = {
  type: BlankType.MULTIPLE_CHOICE;
  keys: Array<string>;
}

export type Answer = SingleChoiceAnswer | MultipleChoiceAnswer;
