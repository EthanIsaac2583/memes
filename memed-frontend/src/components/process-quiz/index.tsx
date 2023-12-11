import {Quiz} from "../../model/quiz";
import {FC, useEffect, useState} from "react";
import {Question} from "../../model/question";
import {ProcessQuestion} from "../process-question";
import {ErrorResponse} from "../../model/error-response";
import {useRepositories} from "../../repository/repositories-context";
import {Item} from "../../model/item";

interface IProps {
  quiz: Quiz;
  onEnd?: (quiz: Quiz) => void;
}

export const ProcessQuiz: FC<IProps> = (props) => {
  const { quiz, onEnd } = props;
  const [questionItem, setQuestionItem] = useState<Item<Question> | null>(null);

  const repositories = useRepositories();

  const handleFetchNextQuestion = () => {
    repositories?.questionRepository
      .nextQuestion(new URLSearchParams())
      .then(setQuestionItem)
      .catch((errorResponse: ErrorResponse) => {
        if (errorResponse.statusCode === 404) {
          onEnd?.(quiz);
        }
      });
  }

  useEffect(() => {
    handleFetchNextQuestion();
  }, []);

  const handleProcessed = () => {
    handleFetchNextQuestion();
  };

  if (questionItem === null) {
    return null;
  }

  return <ProcessQuestion questionItem={questionItem} onProcessed={handleProcessed} />;
};
