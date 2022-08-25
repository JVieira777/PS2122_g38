import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App/App';
import reportWebVitals from './reportWebVitals';
import { MoralisProvider } from 'react-moralis';



//window.localStorage.setItem('access_token', 'f65d8b4a-8bfa-44d5-b985-cf07ab7fe4ee');
//axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('access_token')}`;
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <MoralisProvider appId="Create React App Sample" serverUrl="http://localhost:3000/">
      <App />
    </MoralisProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
