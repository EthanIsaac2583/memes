import {SingleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import {SubmitHandler, useForm} from "react-hook-form";
import {BlankType} from "../../model/blank-type";
import {RectRadio} from "../ui-kit/rect-radio";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Answer} from "../../model/answer";

interface IProps {
  blank: SingleChoiceBlank;
  onSubmit?: (answer: Answer) => void;
}

export const SingleChoiceBlankRenderer: FC<IProps> = (props) => {
  const { blank, onSubmit } = props;

  const methods = useForm<Answer>();

  const handleSubmit: SubmitHandler<Answer> = (answer) => {
    onSubmit?.(answer);
  };

  return (
    <form onSubmit={methods.handleSubmit(handleSubmit)}>
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
