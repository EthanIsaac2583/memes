import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {Quiz} from "../../model/quiz";
import {ProcessQuiz} from "../../components/process-quiz";
import {BaseLayout} from "../../components/base-layout";
import {FinalizeQuiz} from "../../components/finalize-quiz";
import {useRepositories} from "../../repository/repositories-context";

export const ProcessQuizByTemplateId = () => {
  const { templateId } = useParams();
  const repositories = useRepositories();

  const [quiz, setQuiz] = useState<Quiz | null>(null);
  const [ended, setEnded] = useState(false);

  useEffect(() => {
    if (templateId && parseInt(templateId, 10)) {
      repositories?.quizRepository
        .requestByTemplateId(parseInt(templateId, 10))
        .then(setQuiz);
    }
  }, [templateId]);

  const handleEndQuiz = () => {
    setEnded(true);
  };

  const handleFinalizeQuiz = (updatedQuiz: Quiz) => {
    setQuiz(updatedQuiz);
  };

  if (quiz === null) {
    return null;
  }

  return (
    <BaseLayout>
      {ended ? (
        <FinalizeQuiz
          quiz={quiz}
          onFinalize={handleFinalizeQuiz}
        />
      ) : (
        <ProcessQuiz
          quiz={quiz}
          onEnd={handleEndQuiz}
        />
      )}
    </BaseLayout>
  );
};
