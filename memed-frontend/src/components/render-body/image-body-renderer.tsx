import Image from 'react-bootstrap/Image';
import {ImageBody} from "../../model/body";
import {FC} from "react";

interface IProps {
  body: ImageBody;
}

export const ImageBodyRenderer: FC<IProps> = (props) => {
  const { body } = props;

  return (
    <div>
      <div>
        <Image src={body.url} fluid />
      </div>
      <div><p>{body.text}</p></div>
    </div>
  );
};
