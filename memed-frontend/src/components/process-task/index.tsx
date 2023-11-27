import {TTask} from "../../model/task";
import {FC} from "react";
import {RenderQuestion} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {FormProvider, useForm} from "react-hook-form";
import Button from "react-bootstrap/Button";

interface IProps {
  task: TTask;
}

export const ProcessTask: FC<IProps> = (props) => {
  const { task } = props;

  const methods = useForm();

  const onSubmit = (data: unknown) => {
    console.log('-----> on submit', data);
  };

  return (
    <FormProvider {...methods}>
      <Container>
        <Row>
          <Col md={6}>
            {/*<RenderQuestion question={task.question} />*/}
          </Col>
          <Col md={6}>
            <form onSubmit={methods.handleSubmit(onSubmit)}>
              <Row>
                <Col>
                  <RenderBlank blank={task.blank} />
                </Col>
              </Row>
              <Row className="mt-3">
                <Col>
                  <Button type="submit">Submit</Button>
                </Col>
              </Row>
            </form>
          </Col>
        </Row>
      </Container>
    </FormProvider>
  );
};
