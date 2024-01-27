import {Link, useNavigate, useParams} from "react-router-dom";
import {BaseLayout} from "../../components/base-layout";
import {useRepositories} from "../../repository/repositories-context";
import {useEffect, useMemo, useState} from "react";
import {Quiz} from "../../model/quiz";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import {ErrorResponse} from "../../model/error-response";

enum GradeRate {
  BAD = 'BAD',
  OKAY = 'OKAY',
  GOOD = 'GOOD'
}

const GRADE_RATE_TITLE_MAP = {
  [GradeRate.BAD]: "🚨 Needs Improvement",
  [GradeRate.OKAY]: "🌟 Room for Growth",
  [GradeRate.GOOD]: "👏 Well Done!"
}

const GRADE_RATE_HINT_MAP = {
  [GradeRate.BAD]: "Keep refining your skills. Your success story is unfolding! 🚀🛠️",
  [GradeRate.OKAY]: "You're on the right track! Improvement is a journey, not a destination! 🌱🚀",
  [GradeRate.GOOD]: "Fantastic job! Your knowledge shines. The sky's the limit! 🌈✨"
}

export const FinalizeQuizById = () => {
  const navigate = useNavigate();
  const { quizId } = useParams();
  const repositories = useRepositories();

  const [quiz, setQuiz] = useState<Quiz | null>(null);

  const handleGotoRoot = () => {
    navigate("/");
  };

  useEffect(() => {
    if (quizId) {
      repositories?.quizRepository
        .finalizeById(quizId)
        .then(setQuiz)
        .catch((errorResponse: ErrorResponse) => {
          if (errorResponse.statusCode === 409) {
            navigate(`/quizzes/${quizId}/questions/item`)
          }
        });
    }
  }, []);

  const gradeRate = useMemo(() => {
    if (quiz) {
      if (quiz.grade >= 70) {
        return GradeRate.GOOD;
      }

      if (quiz.grade >= 50) {
        return GradeRate.OKAY;
      }
    }

    return GradeRate.BAD;
  }, [quiz]);

  if (quiz === null) {
    return null;
  }

  return (
    <BaseLayout>
      <Container>
        <Row>
          <Col>
            <Alert variant="info">
              <Alert.Heading>{GRADE_RATE_TITLE_MAP[gradeRate]}. Earned grade is {quiz.grade}</Alert.Heading>
              {GRADE_RATE_HINT_MAP[gradeRate]}
            </Alert>
          </Col>
        </Row>
        <Row className="justify-content-center mt-5">
          <Col md={4} className="d-grid">
            <Button onClick={handleGotoRoot} type="button" size="lg">Main page</Button>
          </Col>
        </Row>
      </Container>
    </BaseLayout>
  );
};
