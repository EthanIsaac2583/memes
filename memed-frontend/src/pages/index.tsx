import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {ProcessQuizByTemplateId} from "./process-quiz-by-template-id";
import {FinalizeQuizById} from "./finalize-quiz-by-id";
import {ProcessQuestionItemByQuizId} from "./process-quiz-question-by-item";
import {VisitGuard} from "../components/visit-guard";
import {VisitRequester} from "../components/visit-requester";
import {AuthLoginPage} from "./login";
import {AuthRegisterPage} from "./register";

export const router = createBrowserRouter([
  { path: '/about', element: <AboutPage /> },
  { path: '/', element: (
      <VisitRequester>
        <RootPage />
      </VisitRequester>
    )
  },
  { path: '/templates/:templateId/process-quiz', element: (
      <VisitGuard>
        <ProcessQuizByTemplateId />
      </VisitGuard>
    )
  },
  { path: `/quizzes/:quizId/questions/item`, element: (
      <VisitGuard>
        <ProcessQuestionItemByQuizId />
      </VisitGuard>
    )
  },
  { path: '/quizzes/:quizId/finalize', element: (
      <VisitGuard>
        <FinalizeQuizById />
      </VisitGuard>
    )
  },
  {
    path: '/auth/login', element: <AuthLoginPage />
  },
  {
    path: '/auth/register', element: <AuthRegisterPage />
  }
]);
