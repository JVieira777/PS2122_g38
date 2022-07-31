
import './App.css';
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { GetProduct, GetProducts, CreateProduct } from '../pages/Product'
import { GetSeller } from '../pages/Seller';
import { Header } from '../Components/Header';
import { PaymentPage } from '../pages/PaymentPage'
import { SearchBar } from '../Components/SearchBar'
import { SellerProfile } from'../pages/SellerProfile' 
import { ModeratorProfile } from '../pages/ModeratorProfile';
import { UserProfile } from '../pages/UserProfile';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <>
              <Header />
              <SearchBar placeholder="Enter the Product..." />
            </>
          } />

          <Route path="/product/payment/:id" element={
            <PaymentPage />
          } />

          <Route path="/product" element={<GetProducts />} />

          <Route path="/product/:id" element={<GetProduct />} />

          <Route path="/seller/:id/newProduct" element={<CreateProduct />} />

          <Route path="/seller/:id" element={<GetSeller />} />

          <Route path="/seller/profile/:id" element={<SellerProfile />} />

          <Route path="/moderator/profile/:id" element={<ModeratorProfile />} />

          <Route path="/user/profile/:id" element={<UserProfile />} />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
