import React from "react";
import { createContext, useContext, useMemo, useState } from "react";
import { login as loginRequest, signup as signupRequest } from "../services/authService";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(() => localStorage.getItem("bookstore_token"));
  const [user, setUser] = useState(() => {
    try { return JSON.parse(localStorage.getItem("bookstore_user") || "null"); }
    catch { return null; }
  });

  const persist = (result, fallbackUser) => {
    const nextToken = result.token || `demo-${Date.now()}`;
    const nextUser = result.user || fallbackUser;
    localStorage.setItem("bookstore_token", nextToken);
    localStorage.setItem("bookstore_user", JSON.stringify(nextUser));
    setToken(nextToken);
    setUser(nextUser);
    return nextUser;
  };

  const login = async (payload) => {
    if (import.meta.env.VITE_DEMO_MODE === "true") {
      return persist({ token: `demo-${Date.now()}`, user: { fullName: payload.email?.split("@")[0] || "Pabitra", email: payload.email } }, { email: payload.email });
    }
    return persist(await loginRequest(payload), { email: payload.email });
  };

  const signup = async (payload) => {
    if (import.meta.env.VITE_DEMO_MODE === "true") {
      return persist({ token: `demo-${Date.now()}`, user: { fullName: payload.fullName, email: payload.email, phone: payload.phone } }, payload);
    }
    return persist(await signupRequest(payload), payload);
  };

  const setUserFromServer = (serverUser) => {
    const nextUser = serverUser || null;
    localStorage.setItem("bookstore_user", JSON.stringify(nextUser));
    setUser(nextUser);
  };

  const logout = () => {
    localStorage.removeItem("bookstore_token");
    localStorage.removeItem("bookstore_user");
    setToken(null);
    setUser(null);
  };

  const value = useMemo(() => ({ token, user, isAuthenticated: Boolean(token), login, signup, logout, setUserFromServer }), [token, user]);
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  return useContext(AuthContext);
}
