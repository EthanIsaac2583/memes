import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Quiz} from "../../model/quiz";
import {ProcessQuiz} from "../../components/process-quiz";
import {BaseLayout} from "../../components/base-layout";
import {FinalizeQuiz} from "../../components/finalize-quiz";

export const ProcessQuizByTemplateId = () => {
  const { templateId } = useParams();

  const [quiz, setQuiz] = useState<Quiz | null>(null);
  const [ended, setEnded] = useState(false);

  useEffect(() => {
    axios<Quiz>({
      method: 'GET',
      url: "http://192.168.100.5:8080/api/v1/quizzes/request",
      params: { templateId }
    })
      .then(response => {
        setQuiz(response.data);
      });
  }, [templateId]);

  const handleEndQuiz = () => {
    setEnded(true);
  };

  const handleFinalizeQuiz = () => {
    console.log('-------> handleFinalizeQuiz');
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
