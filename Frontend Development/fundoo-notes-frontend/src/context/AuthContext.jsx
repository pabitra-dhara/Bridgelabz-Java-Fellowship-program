import { createContext, useContext, useMemo, useState } from 'react';
import { authService } from '../services/authService';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem('fundoo_token'));
  const [email, setEmail] = useState(localStorage.getItem('fundoo_email'));

  const login = async (credentials) => {
    const response = await authService.login(credentials);
    const data = response.data?.data ?? response.data ?? {};

    // Supports common LoginResponse names. Adjust one line if your backend
    // uses a different field name.
    const receivedToken =
      data.token ??
      data.accessToken ??
      data.jwt ??
      response.data?.token;

    if (!receivedToken) {
      throw new Error('Login succeeded but JWT token was not found in response.');
    }

    localStorage.setItem('fundoo_token', receivedToken);
    localStorage.setItem('fundoo_email', credentials.email);
    setToken(receivedToken);
    setEmail(credentials.email);
  };

  const logout = () => {
    localStorage.removeItem('fundoo_token');
    localStorage.removeItem('fundoo_email');
    setToken(null);
    setEmail(null);
  };

  const value = useMemo(
    () => ({ token, email, isAuthenticated: Boolean(token), login, logout }),
    [token, email]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  return useContext(AuthContext);
}