import {TBlank, SingleChoiceBlank} from "../../model/blank";
import {FC, useMemo} from "react";
import {EBlankType} from "../../model/blank-type";
import {SingleChoiceBlankRenderer} from "./single-choice-blank-renderer";

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
      <SingleChoiceBlankRenderer blank={blank as SingleChoiceBlank} />
    );
  }

  return <div>Unknown blank</div>;
};
