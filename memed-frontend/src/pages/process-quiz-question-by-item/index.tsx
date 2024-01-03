import {useNavigate, useParams, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRepositories} from "../../repository/repositories-context";
import {ProcessQuestion} from "../../components/process-question";
import {Item} from "../../model/item";
import {Question} from "../../model/question";
import {ErrorResponse} from "../../model/error-response";
import {StandardMapper} from "../../util/standard-mapper";

export const ProcessQuestionItemByQuizId = () => {
  const { quizId: quizIdParam } = useParams();
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const repositories = useRepositories();
  const [questionItem, setQuestionItem] = useState<Item<Question> | null>(null);

  useEffect(() => {
    const quizId = StandardMapper.parseToNumber(quizIdParam);
    const number = StandardMapper.parseToNumber(searchParams.get('number'));

    repositories?.questionRepository
      .nextQuestion(quizId, number)
      .then(setQuestionItem)
      .catch((error: ErrorResponse) => {
        if (error.statusCode === 404) {
          navigate(`/quizzes/${searchParams.get("quizId")}/finalize`);
        }
      });
  }, [repositories, searchParams]);

  const handleNavigate = (number: number) => {
    searchParams.set("number", number.toString());
    setSearchParams(searchParams);
  };

  const handleProcessed = () => {
    navigate(`/quizzes/${searchParams.get("quizId")}/finalize`);
  };

  if (questionItem === null) {
    return null;
  }

  return (
    <ProcessQuestion
      questionItem={questionItem}
      onNavigate={handleNavigate}
      onProcessed={handleProcessed}
    />
  );
}
