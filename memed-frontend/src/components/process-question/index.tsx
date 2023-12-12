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
  questionItem: Item<Question>;
  onProcessed?: () => void;
  onNavigate?: (number: number) => void;
}

export const ProcessQuestion: FC<IProps> = (props) => {
  const { questionItem, onProcessed, onNavigate } = props;

  const repositories = useRepositories();

  const handleSubmitBlank = (answer: Answer) => {
    repositories?.questionRepository
      .provideAnswer(questionItem.content.id, answer)
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
      <Row>
        <Col>
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
        <Col>
          {questionItem.number} / {questionItem.totalItems}
        </Col>
        <Col>
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
          <RenderBlank blank={questionItem.content.task.blank} onSubmitBlank={handleSubmitBlank} />
        </Col>
      </Row>
    </Container>
  );
};
