import React, { createContext, useCallback, useContext, useEffect, useMemo, useState } from "react";
import { addCartItem, getCartItems, removeCartItem, updateCartItem, addWishlist, getWishlist, removeWishlist } from "../services/orderService";
import { getBooks } from "../services/bookService";
import { useAuth } from "./AuthContext";

const StoreContext = createContext(null);

export function StoreProvider({ children }) {
  const { isAuthenticated } = useAuth();
  const [cart, setCart] = useState([]);
  const [wishlist, setWishlist] = useState([]);

  const loadRemote = useCallback(async () => {
    if (!isAuthenticated || import.meta.env.VITE_DEMO_MODE === "true") return;
    try {
      const [remoteCart, books] = await Promise.all([getCartItems(), getBooks()]);
      const byId = new Map(books.map(b => [Number(b.id), b]));
      setCart(remoteCart.map(x => { const b = byId.get(Number(x.productId)); return { ...x, productName: x.productName || b?.bookName, productPrice: Number(x.price || b?.discountPrice || b?.price || 0), image: b?.image }; }));
    } catch (e) { console.error("Failed to fetch cart:", e); }
    try {
      const [remoteWishlist, books] = await Promise.all([getWishlist(), getBooks()]);
      const byId = new Map(books.map(b => [Number(b.id), b]));
      setWishlist(remoteWishlist.map(x => { const b = byId.get(Number(x.productId)); return { ...x, productName: b?.bookName || `Book #${x.productId}`, productPrice: b?.discountPrice || b?.price || 0, author: b?.author, image: b?.image }; }));
    } catch (e) { console.error("Failed to fetch wishlist:", e); }
  }, [isAuthenticated]);

  useEffect(() => { loadRemote(); }, [loadRemote]);

  const addToCart = useCallback(async (book) => {
    if (!isAuthenticated) throw new Error("Please login first");
    const saved = await addCartItem(book);
    const savedItem = { ...saved, productName: book.bookName, productPrice: Number(book.discountPrice ?? book.price ?? 0), image: book.image };
    setCart(prev => [...prev, savedItem]);
  }, [isAuthenticated]);

  const changeQuantity = useCallback(async (item, quantity) => {
    const q = Math.max(1, Number(quantity) || 1);
    const saved = await updateCartItem(item.id, q);
    setCart(prev => prev.map(x => x.id === item.id ? saved : x));
  }, []);

  const removeFromCart = useCallback(async (item) => {
    await removeCartItem(item.id);
    setCart(prev => prev.filter(x => x.id !== item.id));
  }, []);

  const addToWishlist = useCallback(async (book) => {
    if (!isAuthenticated) throw new Error("Please login first");
    const saved = await addWishlist(book.id);
    setWishlist(prev => [...prev, saved]);
  }, [isAuthenticated]);

  const removeFromWishlist = useCallback(async (item) => {
    await removeWishlist(item.productId);
    setWishlist(prev => prev.filter(x => x.id !== item.id));
  }, []);

  const cartCount = cart.reduce((sum, x) => sum + Number(x.quantityToBuy || 1), 0);
  const cartTotal = cart.reduce((sum, x) => sum + Number(x.productPrice || 0) * Number(x.quantityToBuy || 1), 0);
  const value = useMemo(() => ({ cart, wishlist, cartCount, cartTotal, addToCart, changeQuantity, removeFromCart, addToWishlist, removeFromWishlist, reloadStore: loadRemote }), [cart, wishlist, cartCount, cartTotal, addToCart, changeQuantity, removeFromCart, addToWishlist, removeFromWishlist, loadRemote]);
  return <StoreContext.Provider value={value}>{children}</StoreContext.Provider>;
}

export function useStore() { return useContext(StoreContext); }
