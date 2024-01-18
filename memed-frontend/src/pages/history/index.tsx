import {BaseLayout} from "../../components/base-layout";
import {useEffect, useState} from "react";
import {Page} from "../../model/page";
import {Quiz} from "../../model/quiz";
import {useRepositories} from "../../repository/repositories-context";
import Container from "react-bootstrap/Container";

export const HistoryPage = () => {
  const [quizPage, setQuizPage] = useState<Page<Quiz> | null>(null);
  const repositories = useRepositories();

  useEffect(() => {
    repositories?.quizRepository
      .findAll()
      .then(setQuizPage)
      .catch((error) => {
        console.log('-------> error', error);
      });
  }, []);

  return (
    <BaseLayout>
      <Container>
        {quizPage?.content.map(quiz => {
          console.log('-------> ', quiz);
          return <div key={quiz.id}>{quiz.template.name}</div>
        })}
      </Container>
    </BaseLayout>
  );
};
