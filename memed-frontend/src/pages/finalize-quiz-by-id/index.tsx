import {useParams} from "react-router-dom";
import {BaseLayout} from "../../components/base-layout";
import {useRepositories} from "../../repository/repositories-context";
import {useEffect, useState} from "react";
import {Quiz} from "../../model/quiz";
import {QuizStatus} from "../../model/quiz-status";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";

export const FinalizeQuizById = () => {
  const { quizId } = useParams();
  const repositories = useRepositories();

  const [quiz, setQuiz] = useState<Quiz | null>(null);

  const handleFinalizeQuiz = () => {
    if (quiz) {
      repositories?.quizRepository
        .finalizeById(quiz.id)
        .then(setQuiz);
    }
  };

  useEffect(() => {
    if (quizId) {
      // repositories?.quizRepository
      //   .requestByTemplateId(parseInt(templateId, 10))
      //   .then(setQuiz);
    }
  }, [quizId]);

  if (quiz === null) {
    return null;
  }

  return (
    <BaseLayout>
      {quiz.status === QuizStatus.IN_PROGRESS ? (
        <Container>
          <Row>
            <Col>
              <Alert variant="success">
                <Alert.Heading>Congratulations! <span className="h2">🎉🎉🎉</span></Alert.Heading>
                You've completed our awesome quiz. Let's find out how many points you've got
              </Alert>
            </Col>
          </Row>
          <Row className="justify-content-center mt-5">
            <Col md={4} className="d-grid">
              <Button onClick={handleFinalizeQuiz} size="lg" type="button">Get grade</Button>
            </Col>
          </Row>
        </Container>
      ) : (
        <div>you</div>
      )}
    </BaseLayout>
  );
};
