import {FC} from "react";
import {SubmitHandler, useForm} from "react-hook-form";
import {BlankType} from "../../model/blank-type";
import {RectRadio} from "../ui-kit/rect-radio";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Answer} from "../../model/answer";
import {BlankRenderer} from "./types";

export const SingleChoiceBlankRenderer: FC<BlankRenderer> = (props) => {
  const { blank, onSubmitBlank } = props;

  const methods = useForm<Answer>();

  const onSubmit: SubmitHandler<Answer> = (answer) => {
    onSubmitBlank?.(answer);
  };

  return (
    <form onSubmit={methods.handleSubmit(onSubmit)}>
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
              />
            </Col>
          </Row>
        )
      })}
    </form>
  );
};
