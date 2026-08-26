import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Login() {
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const change = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const submit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      await login(form);
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <div className="auth-logo">💡</div>
        <h1>Fundoo Notes</h1>
        <p className="text-muted">Sign in to your notes</p>
        <form onSubmit={submit}>
          <input className="form-control mb-3" name="email" type="email" placeholder="Email" value={form.email} onChange={change} required />
          <input className="form-control mb-3" name="password" type="password" placeholder="Password" value={form.password} onChange={change} required />
          {error && <div className="alert alert-danger">{error}</div>}
          <button className="btn btn-dark w-100" disabled={loading}>{loading ? 'Signing in...' : 'Sign in'}</button>
        </form>
        <p className="mt-3 mb-0">New user? <Link to="/register">Create account</Link></p>
      </div>
    </div>
  );
}