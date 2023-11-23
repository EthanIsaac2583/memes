import {FC, PropsWithChildren} from "react";
import './styles.scss';

export const FormSection: FC<PropsWithChildren> = (props) => {
  return (
    <div className='memed-form-section'>
      {props.children}
    </div>
  );
};
