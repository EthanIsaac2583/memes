import {PlainTextBody, Body, YoutubeVideoBody} from "../../model/body";
import {FC, useMemo} from "react";
import {BodyType} from "../../model/body-type";
import {PlainTextBodyRenderer} from "./plain-text-body-renderer";
import {YoutubeVideoBodyRenderer} from "./youtube-video-body-renderer";

interface IProps {
  body: Body;
}

export const RenderBody: FC<IProps> = (props) => {
  const { body } = props;

  const bodyType = useMemo(() => {
    return body.type;
  }, [body]);

  if (bodyType === BodyType.PLAIN_TEXT) {
    return <PlainTextBodyRenderer question={body as PlainTextBody} />
  }

  if (bodyType === BodyType.IMAGE) {
    return <div>image</div>;
  }

  if (bodyType === BodyType.YOUTUBE_VIDEO) {
    return <YoutubeVideoBodyRenderer question={body as YoutubeVideoBody} />
  }

  return <div>Unknown question type</div>
};
