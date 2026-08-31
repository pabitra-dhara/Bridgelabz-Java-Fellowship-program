import React from "react";
import { Link } from "react-router-dom";
export default function NotFound() { return <section className="empty-state page-not-found"><h1>404</h1><p>Page not found.</p><Link to="/" className="blue-button">Back to Bookstore</Link></section>; }
