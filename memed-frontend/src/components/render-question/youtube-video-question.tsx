import {TYoutubeQuestion} from "../../model/question";
import {FC} from "react";
import './youtube-video-question.scss';

interface IProps {
  question: TYoutubeQuestion;
}

export const YoutubeVideoQuestion: FC<IProps> = (props) => {
  const { question } = props;

  return (
    <div
      className='youtube-video-container'
      dangerouslySetInnerHTML={{ __html: question.markup }}
    />
  );
};
