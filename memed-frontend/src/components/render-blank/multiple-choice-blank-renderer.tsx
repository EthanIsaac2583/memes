import {MultipleChoiceBlank} from "../../model/blank";
import {FC} from "react";

interface IProps {
    blank: MultipleChoiceBlank;
}

export const MultipleChoiceBlankRenderer: FC<IProps> = (props) => {
    const { blank } = props;

    return (
        <div>MultipleChoiceBlankRenderer</div>
    );
};
