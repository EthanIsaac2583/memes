import {useNavigate, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRepositories} from "../../repository/repositories-context";
import {ProcessQuestion} from "../../components/process-question";
import {Item} from "../../model/item";
import {Question} from "../../model/question";

export const ProcessQuizQuestionByItem = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const repositories = useRepositories();
  const [questionItem, setQuestionItem] = useState<Item<Question> | null>(null);

  useEffect(() => {
    repositories?.questionRepository
      .nextQuestion(searchParams)
      .then(setQuestionItem);
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
