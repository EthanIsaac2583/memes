import {Quiz} from "../../model/quiz";
import {FC} from "react";

interface IProps {
  quiz: Quiz;
  onFinalize?: () => void;
}

export const FinalizeQuiz: FC<IProps> = (props) => {
  const { quiz, onFinalize } = props;

  const handleFinalizeQuiz = () => {
    console.log('------> handleFinalizeQuiz ', quiz);
    onFinalize?.();
  };

  return (
    <div>
      <div>finalize quiz</div>
      <div>
        <button onClick={handleFinalizeQuiz} type="button">finalize</button>
      </div>
    </div>
  );
};
