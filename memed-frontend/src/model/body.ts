import {BodyType} from "./body-type";

export type PlainTextBody = {
  type: BodyType.PLAIN_TEXT;
  text: string;
}

export type ImageBody = {
  type: BodyType.IMAGE,
  text: string;
  url: string;
}

export type YoutubeVideoBody = {
  type: BodyType.YOUTUBE_VIDEO,
  markup: string;
}

export type Body = PlainTextBody | ImageBody | YoutubeVideoBody;
