import {Quiz} from "../../model/quiz";
import {FC, useEffect, useState} from "react";
import {Question} from "../../model/question";
import {ProcessQuestion} from "../process-question";
import {ErrorResponse} from "../../model/error-response";
import {useRepositories} from "../../repository/repositories-context";

interface IProps {
  quiz: Quiz;
  onEnd?: () => void;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz, onEnd } = props;
  const [question, setQuestion] = useState<Question | null>(null);

  const repositories = useRepositories();

  const handleFetchNextQuestion = () => {
    repositories?.questionRepository
      .nextQuestion(quiz.id)
      .then(setQuestion)
      .catch((errorResponse: ErrorResponse) => {
        if (errorResponse.statusCode === 404) {
          onEnd?.();
        }
      });
  }

  useEffect(() => {
    handleFetchNextQuestion();
  }, []);

  const handleProcessed = () => {
    handleFetchNextQuestion();
  };

  if (question === null) {
    return null;
  }

  return <ProcessQuestion question={question} onProcessed={handleProcessed} />;
};
