import React from "react";
import { useMemo, useState } from "react";
import { useSearchParams } from "react-router-dom";
import BookCard from "../components/BookCard";
import ResponsiveRender from "../components/ResponsiveRender";
import useBooks from "../hooks/useBooks";
import { demoBooks } from "../utils/demoData";

export default function Home() {
  const { books, loading } = useBooks();
  const [params] = useSearchParams();
  const [sort, setSort] = useState("relevance");
  const q = (params.get("q") || "").toLowerCase();
  const source = books.length ? books : demoBooks;

  const filtered = useMemo(() => {
    const list = source.filter((b) => !q || `${b.bookName} ${b.author}`.toLowerCase().includes(q));
    if (sort === "price-low") return [...list].sort((a, b) => (a.discountPrice ?? a.price) - (b.discountPrice ?? b.price));
    if (sort === "price-high") return [...list].sort((a, b) => (b.discountPrice ?? b.price) - (a.discountPrice ?? a.price));
    return list;
  }, [source, q, sort]);

  return (
    <section className="container bookstore-container">
      <div className="breadcrumb">Home / <span>Books</span></div>
      <div className="listing-head">
        <h1>Books <small>({filtered.length} Items)</small></h1>
        <select value={sort} onChange={(e) => setSort(e.target.value)} aria-label="Sort books">
          <option value="relevance">Sort by relevance</option>
          <option value="price-low">Price low to high</option>
          <option value="price-high">Price high to low</option>
        </select>
      </div>
      {loading && <div className="loading-line">Loading books...</div>}
      <ResponsiveRender>
        {({ isMobile }) => (
          <div className={`book-grid ${isMobile ? "mobile-grid" : ""}`}>
            {filtered.map((book) => <BookCard key={book.id} book={book} />)}
          </div>
        )}
      </ResponsiveRender>
      {!filtered.length && <div className="empty-state">No books found.</div>}
      <div className="pagination-demo"><button disabled>‹</button><b>1</b><span>2</span><span>3</span><span>4</span><span>5</span><span>…</span><span>18</span><button>›</button></div>
    </section>
  );
}
