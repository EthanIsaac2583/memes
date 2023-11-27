import {EBlankType} from "./blank-type";
import {Option} from "./option";

export type SingleChoiceBlank = {
  type: EBlankType.SINGLE_CHOICE;
  options: Array<Option>;
}

export type TBlank = SingleChoiceBlank;
