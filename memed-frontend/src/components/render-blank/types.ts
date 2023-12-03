import {Blank} from "../../model/blank";
import {Answer} from "../../model/answer";

export interface BlankRenderer {
  blank: Blank;
  onSubmitBlank?: (answer: Answer) => void;
}
