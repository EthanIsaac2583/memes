import {useSearchParams} from "react-router-dom";
import {useEffect} from "react";
import {useRepositories} from "../../repository/repositories-context";

export const ProcessQuizQuestionByItem = () => {
  const [searchParams] = useSearchParams();
  const repositories = useRepositories();

  useEffect(() => {
    repositories?.questionRepository
      .nextQuestion(searchParams)
      .then(response => {
        console.log('-------> response', response);
      })
  }, [repositories, searchParams]);

  return null;
}
