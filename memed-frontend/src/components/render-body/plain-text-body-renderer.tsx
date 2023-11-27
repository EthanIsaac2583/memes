import {PlainTextBody} from "../../model/body";
import {FC} from "react";

interface IProps {
  question: PlainTextBody;
}

export const PlainTextBodyRenderer: FC<IProps> = (props) => {
  const { question } = props;

    return (
      <div>
        <p className="h3">{question.text}</p>
      </div>
    );
};
