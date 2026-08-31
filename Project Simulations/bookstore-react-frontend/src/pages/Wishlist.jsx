import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useStore } from "../context/StoreContext";
import { bookImage } from "../utils/demoData";

export default function Wishlist() {
  const { isAuthenticated } = useAuth();
  const { wishlist, removeFromWishlist } = useStore();
  const items = wishlist;
  if (!isAuthenticated) return <section className="wishlist-login"><h1>PLEASE LOG IN</h1><p>Login to view items in your wishlist.</p><div className="heart-book"><i className="bi bi-heart-fill" /></div><Link className="outline-button" to="/login">LOGIN/SIGNUP</Link></section>;
  return <section className="container narrow-page wishlist-page"><div className="breadcrumb">Home / <span>My Wishlist</span></div><div className="wishlist-panel"><h2>My Wishlist ({items.length.toString().padStart(2, "0")})</h2>{items.map((x) => <div className="wishlist-row" key={x.id}><img src={x.image || bookImage(x)} alt="" /><div><strong>{x.productName}</strong><small>by {x.author || "Steve Krug"}</small><b>Rs. {x.productPrice}</b></div><button onClick={() => removeFromWishlist(x)} title="Remove"><i className="bi bi-trash" /></button></div>)}</div></section>;
}
