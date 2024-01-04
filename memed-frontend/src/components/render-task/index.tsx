import {FC, useMemo} from "react";
import {BlankType} from "../../model/blank-type";
import {SingleChoiceTaskRenderer} from "./single-choice-task-renderer";
import {MultipleChoiceTaskRenderer} from "./multiple-choice-task-renderer";
import {TaskRenderer} from "./types";

export const RenderTask: FC<TaskRenderer> = (props) => {
  const { task, onSubmitBlank } = props;

  const blankType = useMemo(() => {
    return task.blank.type;
  }, [task]);

  if (blankType === BlankType.SINGLE_CHOICE) {
    return (
      <SingleChoiceTaskRenderer task={task} onSubmitBlank={onSubmitBlank} />
    );
  }

  if (blankType === BlankType.MULTIPLE_CHOICE) {
    return (
      <MultipleChoiceTaskRenderer task={task} onSubmitBlank={onSubmitBlank} />
    )
  }

  return <div>Unknown blank</div>;
};
