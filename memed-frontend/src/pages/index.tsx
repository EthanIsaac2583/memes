import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {ProcessQuizByTemplateId} from "./process-quiz-by-template-id";
import {FinalizeQuizById} from "./finalize-quiz-by-id";
import {ProcessQuestionItemByQuizId} from "./process-quiz-question-by-item";
import {VisitGuard} from "../components/visit-guard";

export const router = createBrowserRouter([
  { path: '/about', element: <AboutPage /> },
  { path: '/', element: (
      <VisitGuard>
        <RootPage />
      </VisitGuard>
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
]);
