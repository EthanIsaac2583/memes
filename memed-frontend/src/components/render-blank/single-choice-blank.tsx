import {TSingleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import { Form } from "react-bootstrap";

interface IProps {
  blank: TSingleChoiceBlank;
}

export const SingleChoiceBlank: FC<IProps> = (props) => {
  const { blank } = props;

  return (
    <>
      {blank.options.map(option => {
        return (
          <Form.Check
            key={option.key}
            value={option.value}
            label={option.value}
            type="radio"
            name="single-choice-blank-item"
            id={`single-choice-${option.key}-1`}
          />
        )
      })}
    </>
  );
};
