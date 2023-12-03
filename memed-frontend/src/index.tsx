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
  <RepositoriesProvider baseUrl="http://ec2-3-79-152-118.eu-central-1.compute.amazonaws.com">
    <RouterProvider router={router} />
  </RepositoriesProvider>
);
