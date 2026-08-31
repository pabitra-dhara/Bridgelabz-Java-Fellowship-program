import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function OrderSuccess() {
  const { user } = useAuth();
  const address = JSON.parse(localStorage.getItem("last_order_address") || "{}");
  return (
    <section className="container success-page">
      <div className="success-icon"><i className="bi bi-stars" /></div>
      <h1>Order Placed Successfully</h1>
      <p>Hurray!! Your order is confirmed. The order is ready for further communication.</p>
      <div className="success-details"><div><b>Email us</b><span>{user?.email || "admin@bookstore.com"}</span></div><div><b>Contact us</b><span>{address.mobile || "+91 8167894778"}</span></div><div><b>Address</b><span>{address.address || "Bookstore delivery address"}, {address.city || "Bengaluru"}, {address.state || "Karnataka"}</span></div></div>
      <Link to="/" className="blue-button success-button">CONTINUE SHOPPING</Link>
    </section>
  );
}
