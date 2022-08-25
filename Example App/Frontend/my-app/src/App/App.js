
import './App.css';
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { GetProduct, GetProducts, CreateProduct } from '../pages/Product'
import { CreateSeller, GetSeller } from '../pages/Seller';
import { PaymentPage } from '../pages/PaymentPage'
import { SellerProfile } from'../pages/SellerProfile' 
import { ModeratorProfile } from '../pages/ModeratorProfile';
import { UserProfile } from '../pages/UserProfile';
import { RefundRequestbyID, RefundRequests } from '../pages/RefundRequests';
import {Signup,  Login, Logout } from '../pages/Authservice';
import { NavBar } from '../Components/NavBar';
import { SearchPage } from '../pages/SearchPage';
import { UserInfo } from '../pages/UserInfo';
import { SellerProducts } from '../pages/SellerProducts';


//todo create seller page
function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <>
              <NavBar />
              <GetProducts />
            </>
          } />

          <Route path="/product/payment/:id" element={
            <>
            <NavBar />
            <PaymentPage />
            </>
          } />

          <Route path="/search/:name" element={
            <>
            <NavBar />
            <SearchPage />
            </>
          } />

          <Route path="/product" element={
          <>
          <NavBar />
          <GetProducts />
          </>} />

          <Route path="/product/:id" element={
          <>
            <NavBar />
            <GetProduct />
            </>
            } />

          <Route path="/seller/:id/newProduct" element={
          <>
          <NavBar />
          <CreateProduct /> </>
          } />

          <Route path="/seller/:id" element={
          <>
          <NavBar />
          <GetSeller /> </>} />

          <Route path="/seller/profile/:id" element={
          <>
          <NavBar />
          <SellerProfile /> </>} />

          <Route path="/seller/profile/:id/products" element={
          <>
          <NavBar />
          <SellerProducts /> </>} />

          <Route path="/user/profile/:id/seller" element={
          <>
          <NavBar />
          < CreateSeller/> </>} />

          <Route path="/moderator/profile/:id" element={<ModeratorProfile />} />

          <Route path="/user/profile/:id" element={<>
            <NavBar />
          <UserProfile />
          </>} />

          <Route path="/user/info/:id" element={<>
            <NavBar />
          <UserInfo />
          </>} />

          <Route path="/moderator/refundRequest/:id" element={<RefundRequestbyID />} />

          <Route path="/moderator/refundRequest" element={<RefundRequests />} />

          <Route path="/signup" element={<Signup />} />

          <Route path="/login" element={<Login />} />

          <Route path="/logout" element={<Logout />} />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
