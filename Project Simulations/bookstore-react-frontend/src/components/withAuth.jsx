import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export function withAuth(Component) {
  return function AuthenticatedComponent(props) {
    const { isAuthenticated } = useAuth();
    return isAuthenticated ? <Component {...props} /> : <Navigate to="/login" replace />;
  };
}
