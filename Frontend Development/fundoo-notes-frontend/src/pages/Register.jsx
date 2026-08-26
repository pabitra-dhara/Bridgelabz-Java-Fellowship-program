import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

export default function Register() {
  const [form, setForm] = useState({ firstName: '', middleName: '', lastName: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const change = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const submit = async (e) => {
    e.preventDefault();
    setError('');
    setMessage('');
    try {
      await authService.register(form);
      setMessage('Registration successful. You can now sign in.');
      setTimeout(() => navigate('/login'), 700);
    } catch (err) {
      setError(err.response?.data?.message || 'Registration failed');
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card wide">
        <div className="auth-logo">💡</div>
        <h1>Create account</h1>
        <p className="text-muted">Join Fundoo Notes</p>
        <form onSubmit={submit}>
          <div className="row">
            <div className="col-md-4 mb-3"><input className="form-control" name="firstName" placeholder="First name" value={form.firstName} onChange={change} required /></div>
            <div className="col-md-4 mb-3"><input className="form-control" name="middleName" placeholder="Middle name" value={form.middleName} onChange={change} /></div>
            <div className="col-md-4 mb-3"><input className="form-control" name="lastName" placeholder="Last name" value={form.lastName} onChange={change} required /></div>
          </div>
          <input className="form-control mb-3" name="email" type="email" placeholder="Email" value={form.email} onChange={change} required />
          <input className="form-control mb-3" name="password" type="password" placeholder="Password" value={form.password} onChange={change} required />
          {error && <div className="alert alert-danger">{error}</div>}
          {message && <div className="alert alert-success">{message}</div>}
          <button className="btn btn-dark w-100">Create account</button>
        </form>
        <p className="mt-3 mb-0">Already registered? <Link to="/login">Sign in</Link></p>
      </div>
    </div>
  );
}