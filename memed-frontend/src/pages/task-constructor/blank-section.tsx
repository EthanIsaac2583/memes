import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {CloseButton, Form} from "react-bootstrap";
import {BlankType} from "../../model/blank-type";
import Button from "react-bootstrap/Button";
import {FormSection} from "../../components/form-section";
import {useFieldArray, useFormContext} from "react-hook-form";

export const BlankSection = () => {
  const { control, register } = useFormContext();
  const { fields, append, remove } = useFieldArray({
    name: 'blank.options',
    control
  });

  return (
    <FormSection>
      <Row>
        <Col>
          <h3>Blank</h3>
        </Col>
      </Row>
      <Row>
        <Col md={4} xs={12}>
          <Form.Select {...register('blank.type')}>
            <option value={BlankType.SINGLE_CHOICE.toString()}>{BlankType.SINGLE_CHOICE}</option>
          </Form.Select>
        </Col>
      </Row>
      {fields.map((field, i) => {
        return (
          <Row key={field.id} className='mt-3'>
            <Col xs={3} md={4}>
              <Form.Control placeholder="key" {...register(`blank.options.${i}.key`)} />
            </Col>
            <Col xs={7} md={7}>
              <Form.Control placeholder="value" {...register(`blank.options.${i}.value`)} />
            </Col>
            <Col xs={2} md={1} className='d-flex justify-content-center align-items-center'>
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
  );
};
