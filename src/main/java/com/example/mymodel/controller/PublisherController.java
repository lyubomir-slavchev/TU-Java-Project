package com.example.mymodel.controller;

import com.example.mymodel.dto.PublisherDTO;
import com.example.mymodel.model.Publisher;
import com.example.mymodel.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // Извличане на всички издатели
    @GetMapping
    public List<PublisherDTO> getAllPublishers() {
        return publisherService.getAllPublishers().stream()
                .map(publisher -> new PublisherDTO(publisher.getId(), publisher.getName()))
                .collect(Collectors.toList());
    }

    // Създаване на нов издател
    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDto) {
        try {
            Publisher publisher = new Publisher(publisherDto.getName()); // Уверете се, че този конструктор съществува
            Publisher savedPublisher = publisherService.savePublisher(publisher); // Записваме издателя чрез PublisherService

            PublisherDTO responseDTO = new PublisherDTO(savedPublisher.getId(), savedPublisher.getName());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new PublisherDTO(null, "Error creating publisher: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
