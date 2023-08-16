package com.example.springboot.controllers;

import com.example.springboot.dtos.BookRecordDto;
import com.example.springboot.models.BookModel;
import com.example.springboot.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/books")
    public ResponseEntity<BookModel> saveBook(@RequestBody @Valid BookRecordDto bookRecordDto) {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecordDto, bookModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(bookModel));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks() {
        List<BookModel> booksList = bookRepository.findAll();
        if (!booksList.isEmpty()) {
            for (BookModel book : booksList) {
                UUID id = book.getIdBook();
                book.add(linkTo(methodOn(BookController.class).getOneBook(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(booksList);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id) {
        Optional<BookModel> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        book.get().add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("Books List"));
        return ResponseEntity.status(HttpStatus.OK).body(book.get());
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") UUID id, @RequestBody @Valid BookRecordDto bookRecordDto) {
        Optional<BookModel> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }

        var bookModel = book.get();
        BeanUtils.copyProperties(bookRecordDto, bookModel);

        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(bookModel));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") UUID id) {
        Optional<BookModel> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }

        bookRepository.delete(book.get());
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
    }

}
