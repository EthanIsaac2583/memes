import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Quiz} from "../../model/quiz";
import {ProcessQuiz} from "../../components/process-quiz";
import {BaseLayout} from "../../components/base-layout";
import {useRepositories} from "../../repository/repositories-context";

export const ProcessQuizByTemplateId = () => {
  const { templateId } = useParams();
  const navigate = useNavigate();
  const repositories = useRepositories();

  const [quiz, setQuiz] = useState<Quiz | null>(null);

  useEffect(() => {
    if (templateId) {
      repositories?.quizRepository
        .requestByTemplateId(parseInt(templateId, 10))
        .then(setQuiz);
    }
  }, [templateId]);

  const handleEndQuiz = (endedQuiz: Quiz) => {
    navigate(`/quizzes/${endedQuiz.id}/finalize`);
  };

  if (quiz === null) {
    return null;
  }

  return (
    <BaseLayout>
      <ProcessQuiz
        quiz={quiz}
        onEnd={handleEndQuiz}
      />
    </BaseLayout>
  );
};
