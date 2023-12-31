import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {ProcessQuizByTemplateId} from "./process-quiz-by-template-id";
import {FinalizeQuizById} from "./finalize-quiz-by-id";
import {ProcessQuestionItemByQuizId} from "./process-quiz-question-by-item";

export const router = createBrowserRouter([
  { path: '/', element: <RootPage /> },
  { path: '/about', element: <AboutPage /> },
  { path: '/templates/:templateId/process-quiz', element: <ProcessQuizByTemplateId /> },
  { path: `/quizzes/:quizId/questions/item`, element: <ProcessQuestionItemByQuizId /> },
  { path: '/quizzes/:quizId/finalize', element: <FinalizeQuizById /> },
]);
