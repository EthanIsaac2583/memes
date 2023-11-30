import {Quiz} from "../../model/quiz";
import {FC, useEffect, useState} from "react";
import axios from "axios";
import {Question} from "../../model/question";
import {ProcessTask} from "../process-task";

interface IProps {
  quiz: Quiz;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz } = props;
  const [question, setQuestion] = useState<Question | null>(null);

  useEffect(() => {
    axios<Question>({
      method: 'GET',
      url: `http://localhost:8080/api/v1/quizzes/questions/next`,
      params: { quizId: quiz.id }
    })
      .then(response => {
        setQuestion(response.data);
      });
  }, [quiz]);

  if (question === null) {
    return null;
  }

  return <ProcessTask task={question.task} />;
};
