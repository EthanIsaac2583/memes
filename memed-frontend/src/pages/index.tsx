import { createBrowserRouter } from 'react-router-dom';
import {RootPage} from "./root";
import {AboutPage} from "./about";
import {TaskConstructor} from "./task-constructor";

export const router = createBrowserRouter([
  { path: '/', element: <RootPage /> },
  { path: '/about', element: <AboutPage /> },
  { path: '/question-constructor', element: <TaskConstructor /> }
]);
