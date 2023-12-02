import {Quiz} from "../../model/quiz";
import {FC} from "react";
import {useRepositories} from "../../repository/repositories-context";

interface IProps {
  quiz: Quiz;
  onFinalize?: (quiz: Quiz) => void;
}

export const FinalizeQuiz: FC<IProps> = (props) => {
  const { quiz, onFinalize } = props;

  const repositories = useRepositories();

  const handleFinalizeQuiz = () => {
    repositories?.quizRepository
      .finalizeById(quiz.id)
      .then((updatedQuiz) => {
        onFinalize?.(updatedQuiz);
      });
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
