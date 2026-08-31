import { useEffect, useState } from "react";
import { getBooks } from "../services/bookService";

export default function useBooks() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    let active = true;
    getBooks()
      .then((data) => active && setBooks(data))
      .catch((e) => {
        if (active) {
          setError(e.message);
          setBooks([]);
        }
      })
      .finally(() => active && setLoading(false));
    return () => { active = false; };
  }, []);

  return { books, loading, error };
}
