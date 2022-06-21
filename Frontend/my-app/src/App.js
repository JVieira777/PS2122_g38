import logo from './logo.svg';
import './App.css';
import CreateProduct from './Product'
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Signup from './Authservice';

function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
        
        <Route path="/product" element={<CreateProduct />} /> 
        <Route path="/signup" element={<Signup />} /> 
     
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
