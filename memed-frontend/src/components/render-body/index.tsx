import {PlainTextBody, Body, YoutubeVideoBody} from "../../model/body";
import {FC, useMemo} from "react";
import {BodyType} from "../../model/body-type";
import {PlainTextBodyRenderer} from "./plain-text-body-renderer";
import {YoutubeVideoBodyRenderer} from "./youtube-video-body-renderer";

interface IProps {
  question: Body;
}

export const RenderQuestion: FC<IProps> = (props) => {
  const { question } = props;

  const questionType = useMemo(() => {
    return question.type;
  }, [question]);

  if (questionType === BodyType.PLAIN_TEXT) {
    return <PlainTextBodyRenderer question={question as PlainTextBody} />
  }

  if (questionType === BodyType.YOUTUBE_VIDEO) {
    return <YoutubeVideoBodyRenderer question={question as YoutubeVideoBody} />
  }

  return <div>Unknown question type</div>
};
