import {useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";
import {useRepositories} from "../../repository/repositories-context";

export const ProcessQuizByTemplateId = () => {
  const { templateId = '' } = useParams();
  const navigate = useNavigate();
  const repositories = useRepositories();

  useEffect(() => {
    repositories?.quizRepository
      .requestByTemplateId(parseInt(templateId, 10))
      .then(fetchedQuiz => {
        navigate(`/quizzes/${fetchedQuiz.id}/questions/item`);
      });
  }, []);

  return null;
};
