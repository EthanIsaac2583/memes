import {TSingleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import {useFormContext} from "react-hook-form";
import {EBlankType} from "../../model/blank-type";
import {RectRadio} from "../ui-kit/rect-radio";

interface IProps {
  blank: TSingleChoiceBlank;
}

export const SingleChoiceBlank: FC<IProps> = (props) => {
  const { blank } = props;

  const { register } = useFormContext();

  return (
    <>
      <input {...register('type')} defaultValue={EBlankType.SINGLE_CHOICE.toString()} hidden />
      {blank.options.map(option => {
        return (
          <RectRadio
            key={option.key}
            {...register('key', { required: true })}
            id={option.key}
            value={option.key}
            label={option.value}
          />
        )
      })}
    </>
  );
};
