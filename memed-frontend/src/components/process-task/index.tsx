import {TTask} from "../../model/task";
import {FC} from "react";
import {RenderBody} from "../render-body";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {RenderBlank} from "../render-blank";
import {FormProvider, useForm} from "react-hook-form";
import Button from "react-bootstrap/Button";

interface IProps {
  task: TTask;
  onProcessed?: () => void;
}

export const ProcessTask: FC<IProps> = (props) => {
  const { task, onProcessed } = props;

  const methods = useForm();

  const onSubmit = (data: unknown) => {
    console.log('-----> on submit', data);
  };

  return (
    <FormProvider {...methods}>
      <Container>
        <Row>
          <Col md={6}>
            <RenderBody body={task.body} />
          </Col>
          <Col md={6} className="mt-xs-3">
            <form onSubmit={methods.handleSubmit(onSubmit)}>
              <Row>
                <Col>
                  <RenderBlank blank={task.blank} />
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
