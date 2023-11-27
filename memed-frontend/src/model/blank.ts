import {BlankType} from "./blank-type";
import {Option} from "./option";

export type SingleChoiceBlank = {
  type: BlankType.SINGLE_CHOICE;
  options: Array<Option>;
}

export type MultipleChoiceBlank = {
  type: BlankType.MULTIPLE_CHOICE;
  options: Array<Option>;
}

export type TBlank = SingleChoiceBlank | MultipleChoiceBlank;
