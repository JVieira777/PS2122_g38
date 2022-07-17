
import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import {GetProduct,GetProducts,CreateProduct} from '../pages/Product'
import { GetSeller } from '../pages/Seller';
import { Header} from '../Components/Header';
import {PaymentPage} from '../pages/PaymentPage'

function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
      <Route path="/" element={<Header />} /> 
      <Route path="/product/payment/:id" element={<PaymentPage />} /> 
        <Route path="/product" element={<GetProducts />} /> 
        <Route path="/product/:id" element={<GetProduct />} /> 
        <Route path="/seller/:id/newProduct" element={<CreateProduct />} /> 
        <Route path="/seller/:id" element={<GetSeller />} /> 
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
