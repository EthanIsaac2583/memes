import {createContext, FC, PropsWithChildren, useContext, useMemo} from "react";
import {QuestionRepository} from "./question-repository";
import {QuizRepository} from "./quiz-repository";

interface RepositoryContext {
  questionRepository: QuestionRepository;
  quizRepository: QuizRepository;
}

const repositoriesContext = createContext<RepositoryContext | null>(null);

interface IProps {
  baseUrl: string;
}

export const RepositoriesProvider: FC<PropsWithChildren<IProps>> = (props) => {
  const { children, baseUrl } = props;

  const repositories: RepositoryContext = useMemo(() => {
    return {
      questionRepository: new QuestionRepository(baseUrl),
      quizRepository: new QuizRepository(baseUrl)
    }
  }, [baseUrl]);

  return (
    <repositoriesContext.Provider value={repositories}>
      {children}
    </repositoriesContext.Provider>
  );
};

export const useRepositories = () => {
  return useContext(repositoriesContext);
}
