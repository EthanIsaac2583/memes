import {createContext, FC, PropsWithChildren, useContext, useMemo} from "react";
import {QuestionRepository} from "./question-repository";
import {QuizRepository} from "./quiz-repository";
import {TemplateRepository} from "./template-repository";
import {VisitRepository} from "./visit-repository";
import {AuthRepository} from "./auth-repository";

interface RepositoryContext {
  questionRepository: QuestionRepository;
  quizRepository: QuizRepository;
  templateRepository: TemplateRepository;
  visitRepository: VisitRepository;
  authRepository: AuthRepository;
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
      quizRepository: new QuizRepository(baseUrl),
      templateRepository: new TemplateRepository(baseUrl),
      visitRepository: new VisitRepository(baseUrl),
      authRepository: new AuthRepository(baseUrl),
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
