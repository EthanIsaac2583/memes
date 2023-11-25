import {TSingleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import { Form } from "react-bootstrap";
import {useFormContext} from "react-hook-form";

interface IProps {
  blank: TSingleChoiceBlank;
}

export const SingleChoiceBlank: FC<IProps> = (props) => {
  const { blank } = props;

  const { register } = useFormContext();

  return (
    <>
      {blank.options.map(option => {
        return (
          <Form.Check
            {...register('key', { required: true })}
            type="radio"
            key={option.key}
            label={option.value}
            value={option.key}
          />
        )
      })}
    </>
  );
};
