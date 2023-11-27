import {MultipleChoiceBlank} from "../../model/blank";
import {FC} from "react";
import {RectCheckbox} from "../ui-kit/rect-checkbox";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {BlankType} from "../../model/blank-type";
import {useFormContext} from "react-hook-form";

interface IProps {
    blank: MultipleChoiceBlank;
}

export const MultipleChoiceBlankRenderer: FC<IProps> = (props) => {
    const {blank} = props;

    const {register} = useFormContext();

    return (
        <>
            <input {...register('type')} defaultValue={BlankType.MULTIPLE_CHOICE.toString()} hidden/>
            {blank.options.map(option => {
                return (
                    <Row key={option.key} className="mb-2">
                        <Col>
                            <RectCheckbox
                                {...register('keys', {required: true})}
                                id={option.key}
                                value={option.key}
                                label={option.value}
                            />
                        </Col>
                    </Row>
                );
            })}
        </>
    );
};
