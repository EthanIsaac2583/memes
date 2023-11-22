import {EBlankType} from "../blank-type";
import {TOption} from "../option";

export type TSingleChoiceBlank = {

  type: EBlankType.SINGLE_CHOICE;
  options: Array<TOption>;
}
