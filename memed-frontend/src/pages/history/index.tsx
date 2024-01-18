import {BaseLayout} from "../../components/base-layout";
import {useContext, useEffect, useState} from "react";
import {Page} from "../../model/page";
import {Quiz} from "../../model/quiz";
import {useRepositories} from "../../repository/repositories-context";
import Container from "react-bootstrap/Container";
import Card from "react-bootstrap/Card";
import {ErrorResponse} from "../../model/error-response";
import {useNavigate} from "react-router-dom";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {authContext} from "../../components/auth";

export const HistoryPage = () => {
  const [quizPage, setQuizPage] = useState<Page<Quiz> | null>(null);
  const repositories = useRepositories();
  const navigate = useNavigate();
  const authManager = useContext(authContext);

  useEffect(() => {
    repositories?.quizRepository
      .findAll()
      .then(setQuizPage)
      .catch((error: ErrorResponse) => {
        if (error.statusCode === 403) {
          ApplicationLocalStorage.removeItem(StorageKey.Token);
          authManager.setLead(null);
          navigate("/");
        }
      });
  }, []);

  return (
    <BaseLayout>
      <Container>
        {quizPage?.content.map(quiz => {
          return (
            <Card
              key={quiz.id}
              bg="secondary"
              text="light"
              className="mb-2"
            >
              <Card.Header>Status: {quiz.status}</Card.Header>
              <Card.Body>
                <Card.Title>{quiz.template.name}</Card.Title>
                <Card.Text>{quiz.template.description}</Card.Text>
              </Card.Body>
              <Card.Footer>Grade: {quiz.grade}</Card.Footer>
            </Card>
          )
        })}
      </Container>
    </BaseLayout>
  );
};
