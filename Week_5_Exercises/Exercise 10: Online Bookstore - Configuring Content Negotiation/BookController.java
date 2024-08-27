package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all books with content negotiation support
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    // Get a book by ID with content negotiation support
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = Optional.empty();
        if (bookOptional.isPresent()) {
            BookDTO bookDTO = modelMapper.map(bookOptional.get(), BookDTO.class);
            return ResponseEntity.ok(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new book with content negotiation support
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> createBook(@Validated @RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book createdBook = bookRepository.save(book);
        BookDTO createdBookDTO = modelMapper.map(createdBook, BookDTO.class);
        return ResponseEntity.ok(createdBookDTO);
    }

    // Update a book with content negotiation support
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Validated @RequestBody BookDTO bookDTO) {
        Optional<Book> bookOptional = Optional.empty();
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();
            modelMapper.map(bookDTO, bookToUpdate);
            Book updatedBook = bookRepository.save(bookToUpdate);
            BookDTO updatedBookDTO = modelMapper.map(updatedBook, BookDTO.class);
            return ResponseEntity.ok(updatedBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a book by ID with content negotiation support
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
