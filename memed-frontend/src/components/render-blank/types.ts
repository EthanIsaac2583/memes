import {SingleChoiceBlank, Blank} from "../../model/blank";
import {Answer} from "../../model/answer";

interface IProps {
  blank: Blank;
  onSubmit?: (answer: Answer) => void;
}
