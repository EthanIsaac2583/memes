import {TSingleChoiceBlank} from "../../model/blank";
import {FC} from "react";

interface IProps {
  blank: TSingleChoiceBlank;
}

export const SingleChoiceBlank: FC<IProps> = (props) => {
  const { blank } = props;

  return (
    <div>
      {blank.options.map(option => {
        return <div key={option.key}>{option.value}</div>
      })}
    </div>
  );
};
