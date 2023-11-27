import {YoutubeVideoBody} from "../../model/body";
import {FC} from "react";
import './youtube-video-body.scss';

interface IProps {
  question: YoutubeVideoBody;
}

export const YoutubeVideoBody: FC<IProps> = (props) => {
  const { question } = props;

  return (
    <div
      className='youtube-video-container'
      dangerouslySetInnerHTML={{ __html: question.markup }}
    />
  );
};
