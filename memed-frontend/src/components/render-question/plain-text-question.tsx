import {TPlainTextQuestion} from "../../model/question";
import {FC} from "react";

interface IProps {
  question: TPlainTextQuestion;
}

export const PlainTextQuestion: FC<IProps> = (props) => {
  const { question } = props;
  console.log('-0-------_>');

    return <div>{question.body}</div>;
};
