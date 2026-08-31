import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useStore } from "../context/StoreContext";
import { useAuth } from "../context/AuthContext";
import { createOrder } from "../services/orderService";
import { updateUserDetails } from "../services/authService";
import { bookImage } from "../utils/demoData";

export default function Checkout() {
  const { cart, cartTotal } = useStore();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ fullName: user?.fullName || "", mobile: user?.phone || "", address: "", city: "", state: "", type: "Work" });
  const update = (e) => setForm((p) => ({ ...p, [e.target.name]: e.target.value }));

  const submit = async (e) => {
    e.preventDefault();
    const orders = cart.map((x) => ({ product_id: x.productId, product_name: x.productName, product_quantity: x.quantityToBuy || 1, product_price: x.productPrice }));
    try {
      await updateUserDetails({ addressType: form.type, fullAddress: form.address, city: form.city, state: form.state });
      await createOrder(orders);
    } catch (err) {
      console.error("Order failed:", err.response?.data || err);
      alert(err.response?.data?.message || "Order could not be placed. Please try again.");
      return;
    }
    localStorage.setItem("last_order_address", JSON.stringify(form));
    localStorage.setItem("last_order_total", String(cartTotal));
    navigate("/order-success");
  };

  return (
    <section className="container narrow-page checkout-page">
      <div className="breadcrumb">Home / <span>Checkout</span></div>
      <div className="cart-panel">
        <h2>Customer Details <button className="outline-button">Add New Address</button></h2>
        <form onSubmit={submit} className="address-form">
          <div><label>Full Name</label><input name="fullName" value={form.fullName} onChange={update} required /></div>
          <div><label>Mobile Number</label><input name="mobile" value={form.mobile} onChange={update} required /></div>
          <div className="full"><label>Address</label><textarea name="address" value={form.address} onChange={update} required /></div>
          <div><label>city/town</label><input name="city" value={form.city} onChange={update} required /></div>
          <div><label>State</label><input name="state" value={form.state} onChange={update} required /></div>
          <div className="full address-type"><label>Type</label><label><input type="radio" name="type" value="Home" checked={form.type === "Home"} onChange={update} /> Home</label><label><input type="radio" name="type" value="Work" checked={form.type === "Work"} onChange={update} /> Work</label><label><input type="radio" name="type" value="Other" checked={form.type === "Other"} onChange={update} /> Other</label></div>
          <button className="blue-button continue-button">CONTINUE</button>
        </form>
      </div>
      <div className="cart-panel order-summary"><h2>Order summary</h2>{cart.map((x) => <div className="summary-item" key={x.id}><img src={x.image || bookImage(x)} alt="" /><div><strong>{x.productName}</strong><small>by Steve Krug</small><b>Rs. {x.productPrice}</b></div></div>)}<div className="summary-total">Total <strong>Rs. {cartTotal}</strong></div></div>
    </section>
  );
}
