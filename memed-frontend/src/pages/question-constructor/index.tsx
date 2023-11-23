import {BaseLayout} from "../../components/base-layout";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {CloseButton, Form} from "react-bootstrap";
import {EBlankType} from "../../model/blank-type";
import Button from "react-bootstrap/Button";
import axios, {AxiosError} from "axios";
import {TTaskDto} from "../../dto/task";
import {SubmitHandler, useFieldArray, useForm} from "react-hook-form";
import {FormSection} from "../../components/form-section";

export const QuestionConstructor = () => {
  const { handleSubmit, register, control } = useForm<TTaskDto>();
  const { fields, append, remove } = useFieldArray({
    name: 'blank.options',
    control
  })

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
      <Form onSubmit={handleSubmit(submit)}>
        <Container>
          <FormSection>
            <Row>
              <Col>
                <h3>Blank</h3>
              </Col>
            </Row>
            <Row>
              <Col md={4} xs={12}>
                <Form.Select {...register('blank.type')}>
                  <option value={EBlankType.SINGLE_CHOICE.toString()}>{EBlankType.SINGLE_CHOICE}</option>
                </Form.Select>
              </Col>
            </Row>
            {fields.map((field, i) => {
              return (
                <Row key={field.id} className='mt-3'>
                  <Col xs={3} md={3}>
                    <Form.Control placeholder="key" {...register(`blank.options.${i}.key`)} />
                  </Col>
                  <Col xs={7} md={7}>
                    <Form.Control placeholder="value" {...register(`blank.options.${i}.value`)} />
                  </Col>
                  <Col xs={2} md={2} className='d-flex justify-content-center align-items-center'>
                    <CloseButton type="button" onClick={() => remove(i)} />
                  </Col>
                </Row>
              )
            })}
            <Row className='mt-3'>
              <Col>
                <Button
                  variant="outline-dark"
                  type="button"
                  onClick={() => append({ key: '', value: '' })}
                >
                  Add option
                </Button>
              </Col>
            </Row>
          </FormSection>
        </Container>
        <Container>
          <Row className='justify-content-md-center mt-5'>
            <Col xs={12} md={4}>
              <div className="d-grid">
                <Button type="submit" size="lg">Submit</Button>
              </div>
            </Col>
          </Row>
        </Container>
      </Form>
    </BaseLayout>
  );
};
