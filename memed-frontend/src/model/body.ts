import {BodyType} from "./body-type";

export type PlainTextBody = {
  type: BodyType.PLAIN_TEXT;
  body: string;
}

export type YoutubeVideoBody = {
  type: BodyType.YOUTUBE_VIDEO,
  markup: string;
}

export type Body = PlainTextBody | YoutubeVideoBody;
