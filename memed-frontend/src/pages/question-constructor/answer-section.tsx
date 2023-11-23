import {FormSection} from "../../components/form-section";
import Col from "react-bootstrap/Col";
import {Form} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import {useFormContext} from "react-hook-form";

export const AnswerSection = () => {
  const { watch, register } = useFormContext();

  return (
    <FormSection>
      <Row>
        <Col><h3>Answer</h3></Col>
      </Row>
      <Row>
        <Col md={4} xs={12}>
          <Form.Control
            {...register('answer.type')}
            value={watch('blank.type')}
            readOnly
            type="text"
            placeholder="Type"
          />
        </Col>
      </Row>
      <Row className='mt-3'>
        <Col md={4} xs={12}>
          <Form.Control {...register('answer.key')} type="text" placeholder="Correct key" />
        </Col>
      </Row>
    </FormSection>
  );
};
