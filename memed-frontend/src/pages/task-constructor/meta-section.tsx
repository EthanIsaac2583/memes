import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {FormSection} from "../../components/form-section";
import Form from "react-bootstrap/esm/Form";
import {useFormContext} from "react-hook-form";

export const MetaSection = () => {
  const { register } = useFormContext();

  return (
    <FormSection>
      <Row>
        <Col>
          <Form.Group>
            <Form.Label column sm="2">
              Task name
            </Form.Label>
            <Col>
              <Form.Control {...register('name')} type="text" placeholder="Give a name to task" />
            </Col>
          </Form.Group>
        </Col>
      </Row>
    </FormSection>
  )
}
