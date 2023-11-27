import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {FormSection} from "../../components/form-section";
import {useFormContext} from "react-hook-form";
import {Form} from "react-bootstrap";
import {BodyType} from "../../model/body-type";

export const QuestionSection = () => {
  const { register } = useFormContext();

  return (
    <FormSection>
      <Row>
        <Col><h3>Question</h3></Col>
      </Row>
      <Row>
        <Col md={4} xs={12}>
          <Form.Select {...register('question.type')}>
            <option value={BodyType.PLAIN_TEXT}>{BodyType.PLAIN_TEXT}</option>
            <option value={BodyType.YOUTUBE_VIDEO}>{BodyType.YOUTUBE_VIDEO}</option>
          </Form.Select>
        </Col>
      </Row>
      <Row className="mt-3">
        <Col>
          <Form.Control {...register('question.body')} as="textarea" rows={3} />
        </Col>
      </Row>
    </FormSection>
  );
};
