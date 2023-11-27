import {EQuestionType} from "./question-type";

export type TPlainTextQuestion = {
  type: EQuestionType.PLAIN_TEXT;
  body: string;
}

export type TYoutubeQuestion = {
  type: EQuestionType.YOUTUBE_VIDEO,
  markup: string;
}

export type TQuestion = TPlainTextQuestion | TYoutubeQuestion;
