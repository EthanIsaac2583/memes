import {FC} from "react";

interface IProps {
  id?: string;
  value?: string;
  label?: string;
}

export const RectRadio: FC<IProps> = (props) => {
  const { id, label, value, ...otherProps } = props;

  return (
    <label htmlFor={id}>
      <input
        {...otherProps}
        id={id}
        type="radio"
        value={value}
      />
      <span>{label}</span>
    </label>
  )
};
