import {useParams} from "react-router-dom";
import {BaseLayout} from "../../components/base-layout";
import {useRepositories} from "../../repository/repositories-context";
import {useEffect, useMemo, useState} from "react";
import {Quiz} from "../../model/quiz";
import {QuizStatus} from "../../model/quiz-status";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";

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
  [GradeRate.BAD]: "Your effort is appreciated! You've got this! 📚💪",
  [GradeRate.OKAY]: "You're on the right track! Improvement is a journey, not a destination! 🌱🚀",
  [GradeRate.GOOD]: "Fantastic job! Your knowledge shines. The sky's the limit! 🌈✨"
}

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
      repositories?.quizRepository
        .findById(parseInt(quizId, 10))
        .then(setQuiz);
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
        <Container>
          <Row>
            <Col>
              <Alert variant="info">
                <Alert.Heading>{GRADE_RATE_TITLE_MAP[gradeRate]} Earned grade {quiz.grade}</Alert.Heading>
                {GRADE_RATE_HINT_MAP[gradeRate]}
              </Alert>
            </Col>
          </Row>
          <Row className="justify-content-center mt-5">
            <Col md={4} className="d-grid">
              <Button href="/" size="lg">Main page</Button>
            </Col>
          </Row>
        </Container>
      )}
    </BaseLayout>
  );
};
