import {Quiz} from "../../model/quiz";
import {FC, useEffect, useState} from "react";
import axios, {AxiosError, AxiosResponse} from "axios";
import {Question} from "../../model/question";
import {ProcessQuestion} from "../process-question";
import {ErrorResponse} from "../../model/error-response";

interface IProps {
  quiz: Quiz;
  onEnd?: () => void;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz, onEnd } = props;
  const [question, setQuestion] = useState<Question | null>(null);

  useEffect(() => {
    axios({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/quizzes/${quiz.id}/questions/next`
    })
      .then((response: AxiosResponse<Question>) => {
        setQuestion(response.data);
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        if (errorResponse?.response?.data.statusCode === 404) {
          onEnd?.();
        }
      });
  }, [quiz]);

  const handleProcessed = () => {
    axios({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/quizzes/${quiz.id}/questions/next`
    })
      .then((response: AxiosResponse<Question>) => {
        setQuestion(response.data);
      })
      .catch((errorResponse: AxiosError<ErrorResponse>) => {
        if (errorResponse?.response?.data.statusCode === 404) {
          onEnd?.();
        }
      });
  };

  if (question === null) {
    return null;
  }

  return <ProcessQuestion question={question} onProcessed={handleProcessed} />;
};
