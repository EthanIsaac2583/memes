import {TPlainTextQuestion} from "../../model/question";
import {FC} from "react";

interface IProps {
  question: TPlainTextQuestion;
}

export const PlainTextQuestion: FC<IProps> = (props) => {
  const { question } = props;

    return <div>{question.body}</div>;
};
