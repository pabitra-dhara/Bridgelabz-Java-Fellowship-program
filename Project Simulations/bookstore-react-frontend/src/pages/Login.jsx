import React from "react";
import { useRef, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function Login() {
  const { login } = useAuth();
  const [email, setEmail] = useState("");
  const passwordRef = useRef(null); // ref example
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  const submit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await login({ email, password: passwordRef.current.value });
      navigate(location.state?.from || "/");
    } catch (err) {
      setError(err.response?.data?.message || "Login failed. Check your email and password.");
    }
  };

  return (
    <section className="auth-page">
      <div className="auth-art">
        <div className="shopping-illustration"><i className="bi bi-cart4" /></div>
        <strong>ONLINE BOOK SHOPPING</strong>
      </div>
      <div className="auth-card">
        <div className="auth-tabs"><Link className="active" to="/login">LOGIN</Link><Link to="/signup">SIGNUP</Link></div>
        <form onSubmit={submit}>
          <label>Email Id</label><input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          <label>Password</label><input ref={passwordRef} type="password" required />
          <div className="forgot">Forgot Password?</div>
          {error && <div className="form-error">{error}</div>}
          <button className="primary-full">Login</button>
        </form>
        <div className="or"><span>OR</span></div>
        <div className="social-row"><button>Facebook</button><button>Google</button></div>
      </div>
    </section>
  );
}
