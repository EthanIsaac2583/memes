import {ChangeEventHandler, FormEventHandler, useState} from "react";
import {BaseLayout} from "../../components/base-layout";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {Form} from "react-bootstrap";
import {EBlankType} from "../../model/blank-type";
import Button from "react-bootstrap/Button";
import axios, {AxiosError} from "axios";
import {TTaskDto} from "../../dto/task";
import {SubmitHandler, useForm} from "react-hook-form";

export const QuestionConstructor = () => {
  const { handleSubmit } = useForm();
  const [blankType, setBlankType] = useState<EBlankType | null>(null);

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
      })
  };

  const handleSelectBlankType: ChangeEventHandler<HTMLSelectElement> = (e) => {
    setBlankType(e.target.value as EBlankType);
  };

  return (
    <BaseLayout>
      <Form onSubmit={handleSubmit(submit)}>
        <Container>
          <Row>
            <Col md={4} xs={12}>
                <Form.Select name="type" value={blankType?.toString()} onChange={handleSelectBlankType}>
                  <option>Open this select menu</option>
                  <option value={EBlankType.SINGLE_CHOICE.toString()}>{EBlankType.SINGLE_CHOICE}</option>
                </Form.Select>
            </Col>
            <Col><Button type="submit">Submit</Button></Col>
          </Row>
        </Container>
      </Form>
    </BaseLayout>
  );
};
