import React from "react";
import { Link } from "react-router-dom";
import { useStore } from "../context/StoreContext";
import { bookImage } from "../utils/demoData";

export default function BookCard({ book }) {
  const { addToCart, addToWishlist } = useStore();
  const price = Number(book.discountPrice ?? book.price ?? 0);
  const old = Number(book.price ?? price);

  return (
    <article className={`book-card ${Number(book.quantity) === 0 ? "out-of-stock" : ""}`}>
      <Link to={`/book/${book.id}`} className="book-image-wrap">
        <img src={bookImage(book)} alt={book.bookName || "Book"} className="book-cover" />
        {Number(book.quantity) === 0 && <span className="stock-label">OUT OF STOCK</span>}
      </Link>
      <div className="book-info">
        <Link to={`/book/${book.id}`} className="book-title">{book.bookName || "Don't Make Me Think"}</Link>
        <div className="author">by {book.author || "Steve Krug"}</div>
        <div className="rating"><span>{Number(book.rating || 4.5).toFixed(1)} ★</span> ({book.feedbackCount || 20})</div>
        <div className="price-row"><strong>Rs. {price}</strong>{old > price && <del>Rs.{old}</del>}</div>
        <div className="card-actions">
          <button disabled={Number(book.quantity) === 0} onClick={() => addToCart(book)}>ADD TO BAG</button>
          <button className="wishlist-mini" onClick={() => addToWishlist(book)} title="Add to wishlist"><i className="bi bi-heart" /></button>
        </div>
      </div>
    </article>
  );
}
