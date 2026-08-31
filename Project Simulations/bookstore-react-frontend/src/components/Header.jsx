import React from "react";
import { useEffect, useRef, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useStore } from "../context/StoreContext";
import { useClickOutside } from "../hooks/useClickOutside";

export default function Header() {
  const { isAuthenticated, user, logout } = useAuth();
  const { cartCount } = useStore();
  const [query, setQuery] = useState("");
  const [open, setOpen] = useState(false);
  const menuRef = useRef(null);
  const navigate = useNavigate();
  const location = useLocation();

  useClickOutside(menuRef, () => setOpen(false));

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    setQuery(params.get("q") || "");
  }, [location.search]);

  const submitSearch = (e) => {
    e.preventDefault();
    navigate(`/books${query.trim() ? `?q=${encodeURIComponent(query.trim())}` : ""}`);
  };

  return (
    <header className="site-header">
      <div className="header-inner">
        <Link to="/" className="brand">
          <i className="bi bi-book-half" /> <span>Bookstore</span>
        </Link>

        <form className="search-box" onSubmit={submitSearch}>
          <i className="bi bi-search" />
          <input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search ..." aria-label="Search books" />
        </form>

        <nav className="header-actions">
          <div className="profile-menu" ref={menuRef}>
            <button className="header-icon-button" onClick={() => setOpen((v) => !v)}>
              <i className="bi bi-person" />
              <small>{isAuthenticated ? (user?.fullName || "Profile").split(" ")[0] : "Profile"}</small>
            </button>
            {open && (
              <div className="profile-dropdown">
                {isAuthenticated ? (
                  <>
                    <Link to="/profile" onClick={() => setOpen(false)}>Profile</Link>
                    <Link to="/orders" onClick={() => setOpen(false)}>My Orders</Link>
                    <Link to="/wishlist" onClick={() => setOpen(false)}>Wishlist</Link>
                    <button onClick={() => { logout(); setOpen(false); navigate("/"); }}>Logout</button>
                  </>
                ) : (
                  <>
                    <Link to="/login" onClick={() => setOpen(false)}>Login</Link>
                    <Link to="/signup" onClick={() => setOpen(false)}>Signup</Link>
                  </>
                )}
              </div>
            )}
          </div>
          <Link to="/cart" className="header-icon-button cart-button">
            <span className="cart-badge">{cartCount}</span>
            <i className="bi bi-cart3" />
            <small>Cart</small>
          </Link>
        </nav>
      </div>
    </header>
  );
}
