import {SingleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import {useFormContext} from "react-hook-form";
import {BlankType} from "../../model/blank-type";
import {RectRadio} from "../ui-kit/rect-radio";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface IProps {
  blank: SingleChoiceBlank;
}

export const SingleChoiceBlankRenderer: FC<IProps> = (props) => {
  const { blank } = props;

  const { register } = useFormContext();

  return (
    <>
      <input {...register('type')} defaultValue={BlankType.SINGLE_CHOICE.toString()} hidden />
      {blank.options.map(option => {
        return (
          <Row key={option.key} className="mb-2">
            <Col>
              <RectRadio
                {...register('key', { required: true })}
                id={option.key}
                value={option.key}
                label={option.value}
              />
            </Col>
          </Row>
        )
      })}
    </>
  );
};
