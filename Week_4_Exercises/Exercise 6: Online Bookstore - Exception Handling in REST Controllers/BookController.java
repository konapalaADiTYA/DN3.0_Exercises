package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok()
                             .header("Custom-Header", "BookStoreAPI")
                             .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.stream().filter(b -> b.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return ResponseEntity.ok()
                                     .header("Custom-Header", "BookStoreAPI")
                                     .body(updatedBook);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
