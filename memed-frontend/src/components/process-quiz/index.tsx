import {Quiz} from "../../model/quiz";
import {FC, useEffect} from "react";

interface IProps {
  quiz: Quiz;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz } = props;

  useEffect(() => {
    console.log('--------> quiz ', quiz);
  }, [quiz]);

  return <div>{JSON.stringify(quiz)}</div>;
};
