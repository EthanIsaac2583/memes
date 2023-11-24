import {TQuestion} from "../../model/question";
import {FC, useMemo} from "react";
import {EQuestionType} from "../../model/question-type";
import {PlainTextQuestion} from "./plain-text-question";

interface IProps {
  question: TQuestion;
}

export const RenderQuestion: FC<IProps> = (props) => {
  const { question } = props;

  const questionType = useMemo(() => {
    return question.type;
  }, [question]);

  console.log('-------> questionType', questionType);

  if (questionType === EQuestionType.PLAIN_TEXT) {
    return <PlainTextQuestion question={question} />
  }

  return <div>Unknown question type</div>
};
