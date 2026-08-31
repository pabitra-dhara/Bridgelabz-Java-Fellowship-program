import api from "./api";

export async function getBooks() {
  const response = await api.get("/bookstore_book/get/book");
  return Array.isArray(response.data) ? response.data : response.data?.data || [];
}

export async function addFeedback(productId, payload) {
  const response = await api.post(`/bookstore_book/add/feedback/${productId}`, payload);
  return response.data;
}

export async function getFeedback(productId) {
  const response = await api.get(`/bookstore_book/get/feedback/${productId}`);
  return Array.isArray(response.data) ? response.data : response.data?.data || [];
}
