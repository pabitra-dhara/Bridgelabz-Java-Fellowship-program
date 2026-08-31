import axios from "axios";

const api = axios.create({
  // Use relative URLs so Vite proxies API requests to the Spring Boot backend.
  // Do not use VITE_API_BASE_URL here during local development, otherwise
  // the browser bypasses the Vite proxy and CORS errors occur.
  baseURL: "",
  timeout: 10000,
  headers: { "Content-Type": "application/json" }
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("bookstore_token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem("bookstore_token");
      localStorage.removeItem("bookstore_user");
    }
    return Promise.reject(error);
  }
);

export default api;
