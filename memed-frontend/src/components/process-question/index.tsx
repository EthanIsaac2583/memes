import {FC} from "react";
import {RenderBody} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {Question} from "../../model/question";
import {Answer} from "../../model/answer";
import {useRepositories} from "../../repository/repositories-context";

interface IProps {
  question: Question;
  onProcessed?: () => void;
}

export const ProcessQuestion: FC<IProps> = (props) => {
  const { question, onProcessed } = props;

  const repositories = useRepositories();

  const handleSubmitBlank = (answer: Answer) => {
    repositories?.questionRepository
      .provideAnswer(question.id, answer)
      .then(() => {
        onProcessed?.();
      });
  }

  return (
    <Container>
      <Row>
        <Col md={6}>
          <RenderBody body={question.task.body} />
        </Col>
        <Col md={6} className="mt-xs-3">
          <RenderBlank blank={question.task.blank} onSubmitBlank={handleSubmitBlank} />
        </Col>
      </Row>
    </Container>
  );
};
