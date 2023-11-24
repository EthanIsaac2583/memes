import {TTask} from "../../model/task";
import {FC} from "react";
import {RenderQuestion} from "../render-question";

interface IProps {
  task: TTask;
}

export const ProcessTask: FC<IProps> = (props) => {
  const { task } = props;

  return (
    <div>
      <RenderQuestion question={task.question} />
    </div>
  )
};
