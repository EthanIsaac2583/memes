import {TYoutubeQuestion} from "../../model/question";
import {FC} from "react";

interface IProps {
  question: TYoutubeQuestion;
}

export const YoutubeVideoQuestion: FC<IProps> = (props) => {
  const { question } = props;

  return <div dangerouslySetInnerHTML={{ __html: question.markup }}></div>;
};
