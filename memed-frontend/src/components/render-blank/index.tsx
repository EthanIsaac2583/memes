import {FC, useMemo} from "react";
import {BlankType} from "../../model/blank-type";
import {SingleChoiceBlankRenderer} from "./single-choice-blank-renderer";
import {MultipleChoiceBlankRenderer} from "./multiple-choice-blank-renderer";
import {BlankRenderer} from "./types";

export const RenderBlank: FC<BlankRenderer> = (props) => {
  const { question, onSubmitBlank } = props;

  const blankType = useMemo(() => {
    return question.task.blank.type;
  }, [question]);

  if (blankType === BlankType.SINGLE_CHOICE) {
    return (
      <SingleChoiceBlankRenderer question={question} onSubmitBlank={onSubmitBlank} />
    );
  }

  if (blankType === BlankType.MULTIPLE_CHOICE) {
    return (
      <MultipleChoiceBlankRenderer question={question} onSubmitBlank={onSubmitBlank} />
    );
  }

  return <div>Unknown blank</div>;
};
