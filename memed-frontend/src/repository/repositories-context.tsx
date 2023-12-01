import {createContext, FC, PropsWithChildren, useContext} from "react";
import {QuestionRepository} from "./question-repository";

interface RepositoryContext {
  questionRepository: QuestionRepository;
}

const DEFAULT_VALUE: RepositoryContext = {
  questionRepository: new QuestionRepository()
}

const repositoriesContext = createContext<RepositoryContext>(DEFAULT_VALUE);

export const RepositoriesProvider: FC<PropsWithChildren> = (props) => {
  const { children } = props;

  return (
    <repositoriesContext.Provider value={DEFAULT_VALUE}>
      {children}
    </repositoriesContext.Provider>
  );
};

export const useRepositories = () => {
  return useContext(repositoriesContext);
}
