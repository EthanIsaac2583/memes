import {TPlainTextQuestion, TQuestion, TYoutubeQuestion} from "../../model/question";
import {FC, useMemo} from "react";
import {EQuestionType} from "../../model/question-type";
import {PlainTextQuestion} from "./plain-text-question";
import {YoutubeVideoQuestion} from "./youtube-video-question";

interface IProps {
  question: TQuestion;
}

export const RenderQuestion: FC<IProps> = (props) => {
  const { question } = props;

  const questionType = useMemo(() => {
    return question.type;
  }, [question]);

  if (questionType === EQuestionType.PLAIN_TEXT) {
    return <PlainTextQuestion question={question as TPlainTextQuestion} />
  }

  if (questionType === EQuestionType.YOUTUBE_VIDEO) {
    return <YoutubeVideoQuestion question={question as TYoutubeQuestion} />
  }

  return <div>Unknown question type</div>
};
