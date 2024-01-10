import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRepositories} from "../../repository/repositories-context";
import {BaseLayout} from "../../components/base-layout";
import {ErrorResponse} from "../../model/error-response";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";

export const ProcessQuizByTemplateId = () => {
  const { templateId = '' } = useParams();
  const navigate = useNavigate();
  const repositories = useRepositories();
  const [error, setError] = useState<ErrorResponse | undefined>();

  useEffect(() => {
    repositories?.quizRepository
      .requestByTemplateId(parseInt(templateId, 10))
      .then(fetchedQuiz => {
        navigate(`/quizzes/${fetchedQuiz.id}/questions/item`);
      })
      .catch(setError);
  }, []);

  return (
    <BaseLayout>
      <Container>
        <Row>
          <Col>
            {error && (<Alert variant="danger">{error.message}</Alert>)}
          </Col>
        </Row>
      </Container>
    </BaseLayout>
  );
};
