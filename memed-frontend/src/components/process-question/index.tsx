import {FC} from "react";
import {RenderBody} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {Question} from "../../model/question";
import {Answer} from "../../model/answer";
import {useRepositories} from "../../repository/repositories-context";
import {Item} from "../../model/item";
import Button from "react-bootstrap/Button";

interface IProps {
  quizId: string;
  questionItem: Item<Question>;
  onProcessed?: () => void;
  onNavigate?: (number: number) => void;
}

export const ProcessQuestion: FC<IProps> = (props) => {
  const { quizId, questionItem, onProcessed, onNavigate } = props;

  const repositories = useRepositories();

  const handleSubmitBlank = (answer: Answer) => {
    repositories?.questionRepository
      .provideAnswer(quizId, questionItem.content.id, answer)
      .then(() => {
        if (questionItem.last && onProcessed) {
          onProcessed();
        } else if (questionItem.hasNext && onNavigate) {
          onNavigate(questionItem.number + 1);
        }
      });
  }

  return (
    <Container>
      <Row className="mb-5">
        <Col xs={3} className="d-flex align-items-center justify-content-start">
          <Button
            onClick={() => {
              if (questionItem.hasPrevious && onNavigate) {
                onNavigate(questionItem.number - 1);
              }
            }}
            disabled={!questionItem.hasPrevious}
          >
            Previous
          </Button>
        </Col>
        <Col xs={6} className="d-flex align-items-center justify-content-center">
          <span>{questionItem.number} / {questionItem.totalItems}</span>
        </Col>
        <Col xs={3} className="d-flex align-items-center justify-content-end">
          <Button
            onClick={() => {
              if (questionItem.hasNext && onNavigate) {
                onNavigate(questionItem.number + 1);
              }
            }}
            disabled={!questionItem.hasNext}
          >
            Next
          </Button>
        </Col>
      </Row>
      <Row>
        <Col md={6}>
          <RenderBody body={questionItem.content.task.body} />
        </Col>
        <Col md={6} className="mt-xs-3">
          <RenderBlank question={questionItem.content} onSubmitBlank={handleSubmitBlank} />
        </Col>
      </Row>
    </Container>
  );
};
