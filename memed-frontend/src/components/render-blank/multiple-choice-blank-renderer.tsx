import {MultipleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import {RectCheckbox} from "../ui-kit/rect-checkbox";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

interface IProps {
    blank: MultipleChoiceBlank;
}

export const MultipleChoiceBlankRenderer: FC<IProps> = (props) => {
    const { blank } = props;

    return (
        <div>
            {blank.options.map(option => {
                return (
                    <Row key={option.key}>
                        <Col>
                            <RectCheckbox />
                        </Col>
                    </Row>
                );
            })}
        </div>
    );
};
