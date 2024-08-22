package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI	.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
    private ModelMapper modelMapper;

    private List<Book> books =null ;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return books.stream()
                    .map(book -> modelMapper.map(book, BookDTO.class))
                    .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = books.stream().filter(b -> b.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setId((long) (books.size() + 1));
        books.add(book);
        return modelMapper.map(book, BookDTO.class);
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
