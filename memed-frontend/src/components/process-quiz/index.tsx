import {Quiz} from "../../model/quiz";
import {FC} from "react";

interface IProps {
  quiz: Quiz;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz } = props;

  return <div>{JSON.stringify(quiz)}</div>;
};
