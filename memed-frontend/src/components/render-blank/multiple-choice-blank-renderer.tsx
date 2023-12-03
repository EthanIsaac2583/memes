import {FC} from "react";
import {RectCheckbox} from "../ui-kit/rect-checkbox";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {BlankType} from "../../model/blank-type";
import {SubmitHandler, useForm} from "react-hook-form";
import {BlankRenderer} from "./types";
import {Answer} from "../../model/answer";

export const MultipleChoiceBlankRenderer: FC<BlankRenderer> = (props) => {
    const {blank, onSubmitBlank} = props;

    const methods = useForm<Answer>();

  const onSubmit: SubmitHandler<Answer> = (answer) => {
    onSubmitBlank?.(answer);
  };

    return (
        <form onSubmit={methods.handleSubmit(onSubmit)}>
            <p>You can choose several options</p>
            <input {...methods.register('type')} defaultValue={BlankType.MULTIPLE_CHOICE.toString()} hidden/>
            {blank.options.map(option => {
                return (
                    <Row key={option.key} className="mb-2">
                        <Col>
                            <RectCheckbox
                                {...methods.register("keys", {required: true})}
                                id={option.key}
                                value={option.key}
                                label={option.value}
                            />
                        </Col>
                    </Row>
                );
            })}
        </form>
    );
};
