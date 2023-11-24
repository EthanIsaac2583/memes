import {TTask} from "../../model/task";
import {FC} from "react";
import {RenderQuestion} from "../render-question";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface IProps {
  task: TTask;
}

export const ProcessTask: FC<IProps> = (props) => {
  const { task } = props;

  return (
    <Container>
      <Row>
        <Col md={6}>
          <RenderQuestion question={task.question} />
        </Col>
        <Col md={6}>
          <div>here will be blank</div>
        </Col>
      </Row>
    </Container>
  )
};
