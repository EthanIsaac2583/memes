import {FC} from "react";
import {RenderBody} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import Button from "react-bootstrap/Button";
import {Question} from "../../model/question";

interface IProps {
  question: Question;
  onProcessed?: () => void;
}

export const ProcessQuestion: FC<IProps> = (props) => {
  const { question } = props;

  return (
    <Container>
      <Row>
        <Col md={6}>
          <RenderBody body={question.task.body} />
        </Col>
        <Col md={6} className="mt-xs-3">
          <Row>
            <Col>
              <RenderBlank blank={question.task.blank} />
            </Col>
          </Row>
          <Row className="mt-3">
            <Col className="d-grid">
              <Button type="submit" size="lg">Submit</Button>
            </Col>
          </Row>
        </Col>
      </Row>
    </Container>
  );
};
