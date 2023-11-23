import {EQuestionType} from "../question-type";

type TPlainTextQuestion = {
  type: EQuestionType.PLAIN_TEXT;
  body: string;
}

export type TQuestion = TPlainTextQuestion;
