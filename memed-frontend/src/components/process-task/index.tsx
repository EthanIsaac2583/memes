import {FC} from "react";
import {RenderBody} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {FormProvider, SubmitHandler, useForm} from "react-hook-form";
import Button from "react-bootstrap/Button";
import {Answer} from "../../model/answer";
import {Question} from "../../model/question";
import axios from "axios";
import {AnswerDto} from "../../dto/answer-dto";

interface IProps {
  question: Question;
  onProcessed?: () => void;
}

export const ProcessQuestion: FC<IProps> = (props) => {
  const { question, onProcessed } = props;

  const methods = useForm<Answer>();

  const onSubmit: SubmitHandler<Answer> = (answer) => {
    axios({
      method: 'PATCH',
      url: `http://localhost:8080/api/v1/questions/${question?.id}`,
      data: { answer } as AnswerDto
    })
      .then(() => {
        onProcessed?.();
      });
  };

  return (
    <FormProvider {...methods}>
      <Container>
        <Row>
          <Col md={6}>
            <RenderBody body={question.task.body} />
          </Col>
          <Col md={6} className="mt-xs-3">
            <form onSubmit={methods.handleSubmit(onSubmit)}>
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
            </form>
          </Col>
        </Row>
      </Container>
    </FormProvider>
  );
};
