import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {ProcessQuizByTemplateId} from "./process-quiz-by-template-id";
import {FinalizeQuizById} from "./finalize-quiz-by-id";
import {ProcessQuestionItemByQuizId} from "./process-quiz-question-by-item";
import {AuthLoginPage} from "./login";
import {AuthRegisterPage} from "./register";
import {HistoryPage} from "./history";
import {PropertyGuard} from "../components/property-guard";

export const router = createBrowserRouter([
  { path: '/about', element: <AboutPage /> },
  { path: '/', element: <RootPage /> },
  { path: '/templates/:templateId/process-quiz', element: <ProcessQuizByTemplateId /> },
  { path: `/quizzes/:quizId/questions/item`, element: <ProcessQuestionItemByQuizId /> },
  { path: '/quizzes/:quizId/finalize', element: <FinalizeQuizById /> },
  { path: '/auth/login', element: <AuthLoginPage /> },
  { path: '/auth/register', element: <AuthRegisterPage /> },
  {
    path: '/history', element: (
      <PropertyGuard>
        <HistoryPage />
      </PropertyGuard>
    )
  }
]);
