import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {FormSection} from "../../components/form-section";
import {useFormContext} from "react-hook-form";
import {Form} from "react-bootstrap";
import {EQuestionType} from "../../model/question-type";

export const QuestionSection = () => {
  const { register } = useFormContext();

  return (
    <FormSection>
      <Row>
        <Col><h3>Question</h3></Col>
      </Row>
      <Row>
        <Col>
          <Col md={4} xs={12}>
            <Form.Select {...register('question.type')}>
              <option value={EQuestionType.PLAIN_TEXT}>{EQuestionType.PLAIN_TEXT}</option>
              <option value={EQuestionType.YOUTUBE_VIDEO}>{EQuestionType.YOUTUBE_VIDEO}</option>
            </Form.Select>
          </Col>
        </Col>
      </Row>
    </FormSection>
  );
};
