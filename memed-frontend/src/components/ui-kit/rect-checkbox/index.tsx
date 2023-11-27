import {FC, forwardRef} from "react";
import './styles.scss';

interface IProps {
    id?: string;
    value?: string;
    label?: string;
}

export const RectCheckbox: FC<IProps> = forwardRef<HTMLInputElement, IProps>((props, ref) => {
    const { id, label, value, ...otherProps } = props;

    return (
        <label className="rect-checkbox--root">
            <input
                {...otherProps}
                ref={ref}
                id={id}
                type="checkbox"
                value={value}
                className="rect-checkbox--input"
            />
            <span className="rect-checkbox--text">{label}</span>
        </label>
    )
});
