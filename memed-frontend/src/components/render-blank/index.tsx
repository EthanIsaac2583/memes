import {TBlank} from "../../model/blank";
import {FC, useMemo} from "react";
import {EBlankType} from "../../model/blank-type";

interface IProps {
  blank: TBlank;
}

export const RenderBlank: FC<IProps> = (props) => {
  const { blank } = props;

  const blankType = useMemo(() => {
    return blank.type;
  }, [blank]);

  if (blankType === EBlankType.SINGLE_CHOICE) {
    return (
      <div>Single choice blank</div>
    );
  }

  return <div>Unknown blank</div>;
};
