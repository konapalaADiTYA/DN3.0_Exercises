package com.example.BookstoreAPI.controller;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.model.Book;
import com.example.BookstoreAPI.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all books with links
    @GetMapping
    public List<EntityModel<BookDTO>> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> {
                    BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
                    return EntityModel.of(bookDTO,
                            linkTo(methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel(),
                            linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
                })
                .collect(Collectors.toList());
    }

    // Get a book by ID with links
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = Optional.empty();
        if (bookOptional.isPresent()) {
            BookDTO bookDTO = modelMapper.map(bookOptional.get(), BookDTO.class);
            EntityModel<BookDTO> resource = EntityModel.of(bookDTO,
                    linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel(),
                    linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new book with links
    @PostMapping
    public ResponseEntity<EntityModel<BookDTO>> createBook(@Validated @RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book createdBook = bookRepository.save(book);
        BookDTO createdBookDTO = modelMapper.map(createdBook, BookDTO.class);
        EntityModel<BookDTO> resource = EntityModel.of(createdBookDTO,
                linkTo(methodOn(BookController.class).getBookById(createdBookDTO.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
        return ResponseEntity.ok(resource);
    }

    // Update a book with links
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> updateBook(@PathVariable Long id, @Validated @RequestBody BookDTO bookDTO) {
        Optional<Book> bookOptional = Optional.empty();
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();
            modelMapper.map(bookDTO, bookToUpdate);
            Book updatedBook = bookRepository.save(bookToUpdate);
            BookDTO updatedBookDTO = modelMapper.map(updatedBook, BookDTO.class);
            EntityModel<BookDTO> resource = EntityModel.of(updatedBookDTO,
                    linkTo(methodOn(BookController.class).getBookById(updatedBookDTO.getId())).withSelfRel(),
                    linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a book by ID with links
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
