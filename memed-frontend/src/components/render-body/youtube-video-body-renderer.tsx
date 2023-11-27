import {YoutubeVideoBody} from "../../model/body";
import {FC} from "react";
import './youtube-video-body-renderer.scss';

interface IProps {
  body: YoutubeVideoBody;
}

export const YoutubeVideoBodyRenderer: FC<IProps> = (props) => {
  const { body } = props;

  return (
    <div>
      <p className="h3">{body.text}</p>
      <div
          className='youtube-video-container'
          dangerouslySetInnerHTML={{ __html: body.markup }}
      />
    </div>
  );
};
