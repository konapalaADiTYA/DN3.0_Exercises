package com.example.BookstoreAPI.controller;


import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.exception.ResourceNotFoundException;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
	 @Autowired
	 private BookService bookService;
	 
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
    
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Validated @RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(modelMapper.map(createdBook, BookDTO.class));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
