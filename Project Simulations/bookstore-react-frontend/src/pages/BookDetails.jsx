import React from "react";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { demoBooks, bookImage } from "../utils/demoData";
import { getFeedback, addFeedback } from "../services/bookService";
import useBooks from "../hooks/useBooks";
import { useStore } from "../context/StoreContext";
import { useAuth } from "../context/AuthContext";

export default function BookDetails() {
  const { id } = useParams();
  const { books } = useBooks();
  const { addToCart, addToWishlist } = useStore();
  const { isAuthenticated, user } = useAuth();
  const [feedbacks, setFeedbacks] = useState([]);
  const [rating, setRating] = useState(0);
  const [review, setReview] = useState("");
  const book = [...books, ...demoBooks].find((b) => Number(b.id) === Number(id)) || demoBooks[0];

  useEffect(() => {
    getFeedback(id).then(setFeedbacks).catch((e) => { console.error(e); setFeedbacks([]); });
  }, [id]);

  const submitFeedback = async (e) => {
    e.preventDefault();
    if (!isAuthenticated) return;
    const payload = { rating, comment: review };
    try {
      await addFeedback(id, payload);
    } catch (e) {
      console.warn("Feedback API unavailable.", e.message);
    }
    setFeedbacks((prev) => [...prev, { email: user?.email || "You", rating, review }]);
    setReview(""); setRating(0);
  };

  return (
    <section className="container bookstore-container">
      <div className="breadcrumb">Home / <span>Book({String(id).padStart(2, "0")})</span></div>
      <div className="detail-layout">
        <div className="detail-gallery">
          <div className="thumbnail-strip"><img src={bookImage(book)} alt="" /><img src={bookImage(book)} alt="" /></div>
          <div className="main-book-image"><img src={bookImage(book)} alt={book.bookName} /></div>
          <div className="detail-buttons">
            <button onClick={() => addToCart(book)}>ADD TO BAG</button>
            <button className="dark-button" onClick={() => addToWishlist(book)}><i className="bi bi-heart-fill" /> WISHLIST</button>
          </div>
        </div>
        <div className="detail-info">
          <h1>{book.bookName}</h1>
          <div className="author">by {book.author}</div>
          <div className="rating big-rating">{book.rating || 4.5} ★ <span>({book.feedbackCount || 20})</span></div>
          <div className="detail-price">Rs. {book.discountPrice ?? book.price} <del>Rs.{book.price}</del></div>
          <hr />
          <h4>• Book Detail</h4>
          <p>{book.description || "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat."}</p>
          <hr />
          <h3>Customer Feedback</h3>
          <form className="feedback-form" onSubmit={submitFeedback}>
            <label>Overall rating</label>
            <div className="stars-input">{[1,2,3,4,5].map((n) => <button type="button" key={n} onClick={() => setRating(n)} className={n <= rating ? "active" : ""}>★</button>)}</div>
            <textarea value={review} onChange={(e) => setReview(e.target.value)} placeholder="Write your review" required />
            <button className="blue-button" disabled={!isAuthenticated}>Submit</button>
            {!isAuthenticated && <small>Login to submit feedback.</small>}
          </form>
          <div className="feedback-list">
            {feedbacks.map((f, i) => <div className="feedback-item" key={i}><span className="avatar">A</span><div><strong>{f.email || "Customer"}</strong><div className="stars-read">{Array.from({length: 5}, (_, n) => <span key={n} className={n < Number(f.rating || 3) ? "filled" : ""}>★</span>)}</div><p>{f.comment || f.review}</p></div></div>)}
          </div>
        </div>
      </div>
      <Link to="/" className="back-link">← Back to books</Link>
    </section>
  );
}
