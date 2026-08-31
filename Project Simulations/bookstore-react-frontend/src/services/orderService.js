import api from "./api";

export async function addCartItem(book) {
  const response = await api.post(`/bookstore_user/add_cart_item/${book.id}`, {
    productName: book.bookName,
    price: Number(book.discountPrice ?? book.price ?? 0)
  });
  return response.data;
}

export async function getCartItems() {
  const response = await api.get("/bookstore_user/get_cart_items");
  return Array.isArray(response.data) ? response.data : response.data?.data || [];
}

export async function updateCartItem(id, quantityToBuy) {
  const response = await api.put(`/bookstore_user/cart_item_quantity/${id}`, { quantityToBuy });
  return response.data;
}

export async function removeCartItem(id) {
  const response = await api.delete(`/bookstore_user/remove_cart_item/${id}`);
  return response.data;
}

export async function addWishlist(productId) {
  const response = await api.post(`/bookstore_user/add_wish_list/${productId}`);
  return response.data;
}

export async function getWishlist() {
  const response = await api.get("/bookstore_user/get_wishlist_items");
  return Array.isArray(response.data) ? response.data : response.data?.data || [];
}

export async function removeWishlist(productId) {
  const response = await api.delete(`/bookstore_user/remove_wishlist_item/${productId}`);
  return response.data;
}

export async function createOrder(orders) {
  const response = await api.post("/bookstore_user/add/order", { orders });
  return response.data;
}

export async function getOrders() {
  const response = await api.get("/bookstore_user/get_orders");
  return Array.isArray(response.data) ? response.data : response.data?.data || [];
}
