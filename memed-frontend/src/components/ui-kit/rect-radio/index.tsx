import {forwardRef} from "react";
import './styles.scss';

interface IProps {
  id?: string;
  value?: string;
  label?: string;
  checked?: boolean;
}

export const RectRadio = forwardRef<HTMLInputElement, IProps>((props, ref) => {
  const { id, label, value, checked, ...otherProps } = props;

  return (
    <label htmlFor={id} className="rect-radio--root">
      <input
        ref={ref}
        {...otherProps}
        id={id}
        type="radio"
        value={value}
        className="rect-radio--input"
        checked={checked}
      />
      <span className="rect-radio--text">{label}</span>
    </label>
  );
});
