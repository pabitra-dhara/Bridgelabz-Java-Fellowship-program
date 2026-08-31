package com.bookstore.book.controller;

import com.bookstore.book.entity.Book;
import com.bookstore.book.entity.Feedback;
import com.bookstore.book.repository.BookRepository;
import com.bookstore.book.repository.FeedbackRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bookstore_book")
public class BookController {

    private final BookRepository books;
    private final FeedbackRepository feedbacks;

    public BookController(BookRepository books, FeedbackRepository feedbacks) {
        this.books = books;
        this.feedbacks = feedbacks;
    }

    @PostMapping("/admin/add/book")
    @CacheEvict(cacheNames = "books", allEntries = true)
    public ResponseEntity<?> add(@RequestBody Book book) {
        return ResponseEntity.ok(books.save(book));
    }

    @PutMapping("/admin/update/book/{product_id}")
    @CacheEvict(cacheNames = "books", allEntries = true)
    public ResponseEntity<?> update(
            @PathVariable Long product_id,
            @RequestBody Book input) {

        Book b = books.findById(product_id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        b.setBookName(input.getBookName());
        b.setAuthor(input.getAuthor());
        b.setDescription(input.getDescription());
        b.setQuantity(input.getQuantity());
        b.setPrice(input.getPrice());
        b.setDiscountPrice(input.getDiscountPrice());

        return ResponseEntity.ok(books.save(b));
    }

    @DeleteMapping("/admin/delete/book/{product_id}")
    @CacheEvict(cacheNames = "books", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long product_id) {

        books.deleteById(product_id);

        return ResponseEntity.ok(
                Map.of("message", "Book deleted successfully")
        );
    }

    @GetMapping("/get/book")
    @Cacheable("books")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(books.findAll());
    }

    @PostMapping("/add/feedback/{product_id}")
    @CacheEvict(cacheNames = "books", allEntries = true)
    public ResponseEntity<?> addFeedback(
            @PathVariable Long product_id,
            @RequestBody Feedback f,
            Authentication auth) {

        f.setProductId(product_id);
        f.setEmail(auth.getName());

        return ResponseEntity.ok(feedbacks.save(f));
    }

    @GetMapping("/get/feedback/{product_id}")
    public ResponseEntity<?> getFeedback(@PathVariable Long product_id) {
        return ResponseEntity.ok(
                feedbacks.findByProductId(product_id)
        );
    }
}