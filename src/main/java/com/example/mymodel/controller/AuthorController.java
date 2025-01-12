package com.example.mymodel.controller;

import com.example.mymodel.dto.AuthorDTO;
import com.example.mymodel.model.Author;
import com.example.mymodel.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.findAll().stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDto) {
        try {
            Author author = new Author(authorDto.getName()); // Ensure this constructor exists.
            Author savedAuthor = authorService.save(author);
            AuthorDTO responseDTO = new AuthorDTO(savedAuthor.getId(), savedAuthor.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            // Log error if necessary
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating author: " + e.getMessage());
        }
    }
}
