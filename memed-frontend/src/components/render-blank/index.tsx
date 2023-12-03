import {MultipleChoiceBlank, SingleChoiceBlank, Blank} from "../../model/blank";
import {FC, useMemo} from "react";
import {BlankType} from "../../model/blank-type";
import {SingleChoiceBlankRenderer} from "./single-choice-blank-renderer";
import {MultipleChoiceBlankRenderer} from "./multiple-choice-blank-renderer";

interface IProps {
  blank: Blank;
}

export const RenderBlank: FC<IProps> = (props) => {
  const { blank } = props;

  const blankType = useMemo(() => {
    return blank.type;
  }, [blank]);

  if (blankType === BlankType.SINGLE_CHOICE) {
    return (
      <SingleChoiceBlankRenderer blank={blank as SingleChoiceBlank} />
    );
  }

  if (blankType === BlankType.MULTIPLE_CHOICE) {
    return (
      <MultipleChoiceBlankRenderer blank={blank as MultipleChoiceBlank} />
    )
  }

  return <div>Unknown blank</div>;
};
