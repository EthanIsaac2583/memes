import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {TaskConstructor} from "./task-constructor";
import {ProcessQuizByTemplateId} from "./process-quiz-by-template-id";

export const router = createBrowserRouter([
  { path: '/', element: <RootPage /> },
  { path: '/about', element: <AboutPage /> },
  { path: '/task-constructor', element: <TaskConstructor /> },
  { path: '/templates/:templateId/process-quiz', element: <ProcessQuizByTemplateId /> }
]);
