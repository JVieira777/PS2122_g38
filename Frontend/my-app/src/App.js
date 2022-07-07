import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Signup from './Authservice';
import {GetProduct,GetProducts,CreateProduct} from './Product'
import { GetSeller } from './Seller';
import { Header} from './Header';
import {PaymentPage} from './PaymentPage'

function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
      <Route path="/" element={<Header />} /> 
      <Route path="/payment" element={<PaymentPage />} /> 
        <Route path="/product" element={<GetProducts />} /> 
        <Route path="/product/:id" element={<GetProduct />} /> 
        <Route path="/signup" element={<Signup />} /> 
        <Route path="/seller/:id/newProduct" element={<CreateProduct />} /> 
        <Route path="/seller/:id" element={<GetSeller />} /> 
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
