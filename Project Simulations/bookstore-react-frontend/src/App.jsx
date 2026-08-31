import React, { Profiler } from "react";
import { Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ProtectedRoute from "./components/ProtectedRoute";
import Home from "./pages/Home";
import BookDetails from "./pages/BookDetails";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import OrderSuccess from "./pages/OrderSuccess";
import Profile from "./pages/Profile";
import Orders from "./pages/Orders";
import Wishlist from "./pages/Wishlist";
import NotFound from "./pages/NotFound";

function profilerCallback(id, phase, actualDuration) {
  if (import.meta.env.DEV) {
    console.debug(`[Profiler] ${id} ${phase}: ${actualDuration.toFixed(2)}ms`);
  }
}

export default function App() {
  return (
    <div className="app-shell">
      <Header />
      <main className="page-area">
        <Profiler id="BookstoreRoutes" onRender={profilerCallback}>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/books" element={<Home />} />
            <Route path="/book/:id" element={<BookDetails />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/checkout" element={<ProtectedRoute><Checkout /></ProtectedRoute>} />
            <Route path="/order-success" element={<ProtectedRoute><OrderSuccess /></ProtectedRoute>} />
            <Route path="/profile" element={<ProtectedRoute><Profile /></ProtectedRoute>} />
            <Route path="/orders" element={<ProtectedRoute><Orders /></ProtectedRoute>} />
            <Route path="/wishlist" element={<Wishlist />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </Profiler>
      </main>
      <Footer />
    </div>
  );
}
