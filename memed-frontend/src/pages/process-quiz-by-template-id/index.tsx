import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Quiz} from "../../model/quiz";
import {ProcessQuiz} from "../../components/process-quiz";
import {BaseLayout} from "../../components/base-layout";

export const ProcessQuizByTemplateId = () => {
  const { templateId } = useParams();

  const [quiz, setQuiz] = useState<Quiz | null>(null);
  const [isEnded, setEnded] = useState(false);

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

  if (quiz === null) {
    return null;
  }

  if (isEnded) {
    return <div>you ended quiz</div>;
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
