import {FC} from "react";
import {SubmitHandler, useForm} from "react-hook-form";
import {BlankType} from "../../model/blank-type";
import {RectRadio} from "../ui-kit/rect-radio";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Answer, SingleChoiceAnswer} from "../../model/answer";
import {BlankRenderer} from "./types";
import Button from "react-bootstrap/Button";
import {SingleChoiceBlank} from "../../model/blank";

export const SingleChoiceBlankRenderer: FC<BlankRenderer> = (props) => {
  const {question, onSubmitBlank } = props;

  const blank = question.task.blank as SingleChoiceBlank;
  const userAnswer = question.answer as SingleChoiceAnswer | null;

  const methods = useForm<Answer>();

  const onSubmit: SubmitHandler<Answer> = (answer) => {
    onSubmitBlank?.(answer);
  };

  return (
    <form onSubmit={methods.handleSubmit(onSubmit)}>
      <Row>
        <Col>
          <input {...methods.register('type')} defaultValue={BlankType.SINGLE_CHOICE.toString()} hidden />
          {blank.options.map(option => {
            return (
              <Row key={option.key} className="mb-2">
                <Col>
                  <RectRadio
                    {...methods.register('key', { required: true })}
                    id={option.key}
                    value={option.key}
                    label={option.value}
                    defaultChecked={option.key === userAnswer?.key}
                  />
                </Col>
              </Row>
            )
          })}
        </Col>
      </Row>
      <Row className="mt-3">
        <Col className="d-grid">
          <Button type="submit" size="lg">Submit</Button>
        </Col>
      </Row>
    </form>
  );
};
