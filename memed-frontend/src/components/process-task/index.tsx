import {TTask} from "../../model/task";
import {FC} from "react";
import {RenderQuestion} from "../render-question";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {FormProvider, useForm} from "react-hook-form";

interface IProps {
  task: TTask;
}

export const ProcessTask: FC<IProps> = (props) => {
  const { task } = props;

  const methods = useForm();

  const onSubmit = () => {
    console.log('-----> on submit');
  };

  return (
    <FormProvider {...methods}>
      <Container>
        <Row>
          <Col md={6}>
            <RenderQuestion question={task.question} />
          </Col>
          <Col md={6}>
            <form onSubmit={methods.handleSubmit(onSubmit)}>
              <RenderBlank blank={task.blank} />
            </form>
          </Col>
        </Row>
      </Container>
    </FormProvider>
  );
};
