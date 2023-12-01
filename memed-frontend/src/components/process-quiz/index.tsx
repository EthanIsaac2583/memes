import {Quiz} from "../../model/quiz";
import {FC, useEffect, useState} from "react";
import axios from "axios";
import {Question} from "../../model/question";
import {ProcessQuestion} from "../process-question";

interface IProps {
  quiz: Quiz;
  onEnded?: () => void;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz, onEnded } = props;
  const [question, setQuestion] = useState<Question | null>(null);

  useEffect(() => {
    axios<Question>({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/quizzes/${quiz.id}/questions/next`
    })
      .then(response => {
        setQuestion(response.data);
      })
      .catch(errorResponse => {
        console.log('--------> errorResponse 1', errorResponse);
        onEnded?.();
      });
  }, [quiz]);

  const handleProcessed = () => {
    axios<Question>({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/quizzes/${quiz.id}/questions/next`
    })
      .then(response => {
        setQuestion(response.data);
      })
      .catch(errorResponse => {
        console.log('--------> errorResponse 2', errorResponse);
        onEnded?.();
      });
  };

  if (question === null) {
    return null;
  }

  return <ProcessQuestion question={question} onProcessed={handleProcessed} />;
};
