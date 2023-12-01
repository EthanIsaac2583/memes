import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Quiz} from "../../model/quiz";
import {ProcessQuiz} from "../../components/process-quiz";
import {BaseLayout} from "../../components/base-layout";

export const ProcessQuizByTemplateId = () => {
  const { templateId } = useParams();

  const [quiz, setQuiz] = useState<Quiz | null>(null);

  useEffect(() => {
    axios<Quiz>({
      method: 'POST',
      url: "http://localhost:8080/api/v1/quizzes/request",
      data: { templateId }
    })
      .then(response => {
        setQuiz(response.data);
      });
  }, [templateId]);

  if (quiz === null) {
    return null;
  }

  return (
    <BaseLayout>
      <ProcessQuiz quiz={quiz} />
    </BaseLayout>
  );
};