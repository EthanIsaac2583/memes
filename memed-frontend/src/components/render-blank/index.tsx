import {FC, useMemo} from "react";
import {BlankType} from "../../model/blank-type";
import {SingleChoiceBlankRenderer} from "./single-choice-blank-renderer";
import {MultipleChoiceBlankRenderer} from "./multiple-choice-blank-renderer";
import {BlankRenderer} from "./types";

export const RenderBlank: FC<BlankRenderer> = (props) => {
  const { blank, onSubmitBlank } = props;

  const blankType = useMemo(() => {
    return blank.type;
  }, [blank]);

  if (blankType === BlankType.SINGLE_CHOICE) {
    return (
      <SingleChoiceBlankRenderer blank={blank} onSubmitBlank={onSubmitBlank} />
    );
  }

  if (blankType === BlankType.MULTIPLE_CHOICE) {
    return (
      <MultipleChoiceBlankRenderer blank={blank} onSubmitBlank={onSubmitBlank} />
    )
  }

  return <div>Unknown blank</div>;
};
