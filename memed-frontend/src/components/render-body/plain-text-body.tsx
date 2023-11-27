import {PlainTextBody} from "../../model/body";
import {FC} from "react";

interface IProps {
  question: PlainTextBody;
}

export const PlainTextQuestion: FC<IProps> = (props) => {
  const { question } = props;

    return <div>{question.body}</div>;
};
