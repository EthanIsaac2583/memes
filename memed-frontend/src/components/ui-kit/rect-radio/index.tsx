import {forwardRef} from "react";
import './styles.scss';

interface IProps {
  id?: string;
  value?: string;
  label?: string;
}

export const RectRadio = forwardRef<HTMLInputElement, IProps>((props, ref) => {
  const { id, label, value, ...otherProps } = props;

  return (
    <label htmlFor={id} className="rect-radio--root">
      <input
        ref={ref}
        {...otherProps}
        id={id}
        type="radio"
        value={value}
        className="rect-radio--input"
      />
      <span className="rect-radio--text">{label}</span>
    </label>
  );
});
