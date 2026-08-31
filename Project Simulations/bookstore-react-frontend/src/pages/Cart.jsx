import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useStore } from "../context/StoreContext";
import { useAuth } from "../context/AuthContext";
import { bookImage } from "../utils/demoData";

export default function Cart() {
  const { cart, cartCount, cartTotal, changeQuantity, removeFromCart } = useStore();
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();

  return (
    <section className="container narrow-page">
      <div className="breadcrumb">Home / <span>My cart</span></div>
      <div className="cart-panel">
        <div className="cart-head"><h2>My cart ({cartCount})</h2><div className="location-box"><i className="bi bi-geo-alt-fill" /> Use current location⌄</div></div>
        {cart.length === 0 ? <div className="empty-state">Your cart is empty.<br /><Link to="/">Continue shopping</Link></div> :
          cart.map((item) => (
            <div className="cart-item" key={item.id}>
              <img src={item.image || bookImage(item)} alt={item.productName} />
              <div className="cart-item-main"><strong>{item.productName || "Don't Make Me Think"}</strong><small>by Steve Krug</small><b>Rs. {item.productPrice}</b>
                <div className="quantity"><button onClick={() => changeQuantity(item, (item.quantityToBuy || 1) - 1)} disabled={(item.quantityToBuy || 1) <= 1}>−</button><span>{item.quantityToBuy || 1}</span><button onClick={() => changeQuantity(item, (item.quantityToBuy || 1) + 1)}>+</button><button className="remove" onClick={() => removeFromCart(item)}>Remove</button></div>
              </div>
            </div>
          ))
        }
        {cart.length > 0 && <button className="blue-button place-order" onClick={() => isAuthenticated ? navigate("/checkout") : navigate("/login", { state: { from: "/checkout" } })}>PLACE ORDER</button>}
      </div>
      <div className="accordion-static">Address Details <span>⌄</span></div>
      <div className="accordion-static">Order summary <span>Rs. {cartTotal}</span></div>
    </section>
  );
}
