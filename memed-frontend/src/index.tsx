import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import {router} from "./pages";
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import {RepositoriesProvider} from "./repository/repositories-context";
import {AuthProvider} from "./components/auth";
import {VisitGuard} from "./components/visit-guard";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <RepositoriesProvider baseUrl={process.env.REACT_APP_API_URL}>
    <VisitGuard>
      <AuthProvider>
        <RouterProvider router={router} />
      </AuthProvider>
    </VisitGuard>
  </RepositoriesProvider>
);
