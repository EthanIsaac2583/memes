import {YoutubeVideoBody} from "../../model/body";
import {FC} from "react";
import './youtube-video-body-renderer.scss';

interface IProps {
  question: YoutubeVideoBody;
}

export const YoutubeVideoBodyRenderer: FC<IProps> = (props) => {
  const { question } = props;

  return (
    <div
      className='youtube-video-container'
      dangerouslySetInnerHTML={{ __html: question.markup }}
    />
  );
};
