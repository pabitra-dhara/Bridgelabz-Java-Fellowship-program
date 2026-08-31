import React from "react";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function Signup() {
  const { signup } = useAuth();
  const [form, setForm] = useState({ fullName: "", email: "", password: "", phone: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const update = (e) => setForm((p) => ({ ...p, [e.target.name]: e.target.value }));

  const submit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await signup(form);
      navigate("/");
    } catch (err) {
      setError(err.response?.data?.message || "Signup failed.");
    }
  };

  return (
    <section className="auth-page">
      <div className="auth-art">
        <div className="shopping-illustration"><i className="bi bi-cart4" /></div>
        <strong>ONLINE BOOK SHOPPING</strong>
      </div>
      <div className="auth-card">
        <div className="auth-tabs"><Link to="/login">LOGIN</Link><Link className="active" to="/signup">SIGNUP</Link></div>
        <form onSubmit={submit}>
          <label>Full Name</label><input name="fullName" value={form.fullName} onChange={update} required />
          <label>Email Id</label><input name="email" type="email" value={form.email} onChange={update} required />
          <label>Password</label><input name="password" type="password" value={form.password} onChange={update} required minLength={6} />
          <label>Mobile Number</label><input name="phone" value={form.phone} onChange={update} required />
          {error && <div className="form-error">{error}</div>}
          <button className="primary-full">Signup</button>
        </form>
      </div>
    </section>
  );
}
