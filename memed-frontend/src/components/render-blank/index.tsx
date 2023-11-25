import {TBlank, TSingleChoiceBlank} from "../../model/blank";
import {FC, useMemo} from "react";
import {EBlankType} from "../../model/blank-type";
import {SingleChoiceBlank} from "./single-choice-blank";

interface IProps {
  blank: TBlank;
}

export const RenderBlank: FC<IProps> = (props) => {
  const { blank } = props;

  const blankType = useMemo(() => {
    return blank.type;
  }, [blank]);

  if (blankType === EBlankType.SINGLE_CHOICE) {
    return (
      <SingleChoiceBlank blank={blank as TSingleChoiceBlank} />
    );
  }

  return <div>Unknown blank</div>;
};
