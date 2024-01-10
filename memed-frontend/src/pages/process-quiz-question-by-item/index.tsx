import {useNavigate, useParams, useSearchParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRepositories} from "../../repository/repositories-context";
import {ProcessQuestion} from "../../components/process-question";
import {Item} from "../../model/item";
import {Question} from "../../model/question";
import {ErrorResponse} from "../../model/error-response";
import {BaseLayout} from "../../components/base-layout";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";

export const ProcessQuestionItemByQuizId = () => {
  const { quizId = '' } = useParams();
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const repositories = useRepositories();
  const [questionItem, setQuestionItem] = useState<Item<Question> | null>(null);
  const [error, setError] = useState<ErrorResponse | undefined>();

  useEffect(() => {
    repositories?.questionRepository
      .nextQuestion(quizId, searchParams.get('number'))
      .then(setQuestionItem)
      .catch((errorResponse: ErrorResponse) => {
        if (errorResponse.statusCode === 404) {
          navigate(`/quizzes/${quizId}/finalize`);
        } else {
          setError(errorResponse);
        }
      });
  }, [repositories, searchParams]);

  const handleNavigate = (number: number) => {
    searchParams.set("number", number.toString());
    setSearchParams(searchParams);
  };

  const handleProcessed = () => {
    navigate(`/quizzes/${quizId}/finalize`);
  };

  return (
    <BaseLayout>
      {error && (
        <Container>
          <Row>
            <Col>{error && (<Alert variant="danger">{error.message}</Alert>)}</Col>
          </Row>
        </Container>
      )}
      {questionItem && (
        <ProcessQuestion
          quizId={quizId}
          questionItem={questionItem}
          onNavigate={handleNavigate}
          onProcessed={handleProcessed}
        />
      )}

    </BaseLayout>
  );
}
