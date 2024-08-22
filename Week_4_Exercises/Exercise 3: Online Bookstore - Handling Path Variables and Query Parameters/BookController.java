package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                                (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId((long) (books.size() + 1)); 
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
