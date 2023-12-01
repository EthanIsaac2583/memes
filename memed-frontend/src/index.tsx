import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import {router} from "./pages";
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import {RepositoriesProvider} from "./repository/repositories-context";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <RepositoriesProvider>
    <RouterProvider router={router} />
  </RepositoriesProvider>
);
