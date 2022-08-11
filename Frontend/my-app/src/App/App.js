
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
import { RefundRequestbyID, RefundRequests } from '../pages/RefundRequests';
import {Signup,  Login } from '../pages/Authservice';
import {Home} from '../pages/Home'

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <>
              <Header />
              <Home/>
              <SearchBar placeholder="Enter the Product..." />
            </>
          } />

          <Route path="/product/payment/:id" element={
            <>
            <Header />
            <PaymentPage />
            </>
          } />

          <Route path="/product" element={
          <>
          <Header />
          <GetProducts />
          </>} />

          <Route path="/product/:id" element={
          <>
            <Header />
            <GetProduct />
            </>
            } />

          <Route path="/seller/:id/newProduct" element={
          <>
          <Header />
          <CreateProduct /> </>
          } />

          <Route path="/seller/:id" element={
          <>
          <Header />
          <GetSeller /> </>} />

          <Route path="/seller/profile/:id" element={
          <>
          <Header />
          <SellerProfile /> </>} />

          <Route path="/moderator/profile/:id" element={<ModeratorProfile />} />

          <Route path="/user/profile/:id" element={<>
            <Header />
          <UserProfile />
          </>} />

          <Route path="/moderator/refundRequest/:id" element={<RefundRequestbyID />} />

          <Route path="/moderator/refundRequest" element={<RefundRequests />} />

          <Route path="/Signup" element={<Signup />} />

          <Route path="/login" element={<Login />} />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
