import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import {router} from "./pages";
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import {RepositoriesProvider} from "./repository/repositories-context";
import {AuthProvider} from "./components/auth";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <RepositoriesProvider baseUrl="http://localhost:8080">
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </RepositoriesProvider>
);
