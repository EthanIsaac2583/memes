import {EBlankType} from "../blank-type";
import {TOption} from "../option";

type TSingleChoiceBlank = {

  type: EBlankType.SINGLE_CHOICE;
  options: Array<TOption>;
}

export type TBlank = TSingleChoiceBlank;
