import api from "./api";

const normalizeToken = (data) =>
  data?.token || data?.accessToken || data?.jwt || data?.data?.token || null;

const normalizeUser = (data) =>
  data?.user || data?.data?.user || data?.data || data?.userDetails || data;

export async function login(payload) {
  // Change this endpoint here if your Spring Boot auth endpoint has a different path.
  const response = await api.post("/bookstore_user/login", payload);
  return { token: normalizeToken(response.data), user: normalizeUser(response.data) };
}

export async function signup(payload) {
  // Change this endpoint here if your Spring Boot signup endpoint has a different path.
  const response = await api.post("/bookstore_user/registration", payload);
  return { token: normalizeToken(response.data), user: normalizeUser(response.data) };
}

export async function getCurrentUser() {
  const response = await api.get("/bookstore_user/get_user");
  return response.data;
}

export async function getUserDetails() {
  const response = await api.get("/bookstore_user/get_user_details");
  return response.data;
}

export async function updateUserDetails(payload) {
  const response = await api.put("/bookstore_user/edit_user", payload);
  return response.data;
}
