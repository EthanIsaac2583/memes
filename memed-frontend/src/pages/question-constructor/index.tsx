import {BaseLayout} from "../../components/base-layout";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {Form} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import axios, {AxiosError} from "axios";
import {TTaskDto} from "../../dto/task";
import {SubmitHandler, useForm, FormProvider} from "react-hook-form";
import {BlankSection} from "./blank-section";
import {AnswerSection} from "./answer-section";

export const QuestionConstructor = () => {
  const methods = useForm<TTaskDto>();

  const submit: SubmitHandler<TTaskDto> = (data) => {
    axios<TTaskDto, unknown>({
      method: 'POST',
      url: 'http://192.168.100.5:8080/api/v1/tasks',
      data
    })
      .then((response) => {
        console.log('-------> response', response);
      })
      .catch((error: AxiosError) => {
        console.log('--------> error', error.response?.data);
      });
  };

  return (
    <BaseLayout>
      <FormProvider {...methods}>
        <Form onSubmit={methods.handleSubmit(submit)}>
          <Container>
            <BlankSection />
          </Container>
          <Container className="mt-4">
            <AnswerSection />
          </Container>
          <Container className="mt-4">
            <Row className='justify-content-md-center mt-5'>
              <Col xs={12} md={4}>
                <div className="d-grid">
                  <Button type="submit" size="lg">Submit</Button>
                </div>
              </Col>
            </Row>
          </Container>
        </Form>
      </FormProvider>
    </BaseLayout>
  );
};
