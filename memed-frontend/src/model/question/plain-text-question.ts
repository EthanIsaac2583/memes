import {EQuestionType} from "../question-type";

export type TPlainTextQuestion = {
  type: EQuestionType.PLAIN_TEXT;
  body: string;
}
