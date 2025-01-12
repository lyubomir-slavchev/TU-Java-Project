package com.example.mymodel.controller;

import com.example.mymodel.dto.BookDTO;
import com.example.mymodel.model.Book;
import com.example.mymodel.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDto) {
        try {
            Book book = new Book(bookDto.getTitle());
            Book savedBook = bookService.save(book);
            BookDTO responseDTO = new BookDTO(savedBook.getId(), savedBook.getTitle());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating book: " + e.getMessage());
        }
    }
}
