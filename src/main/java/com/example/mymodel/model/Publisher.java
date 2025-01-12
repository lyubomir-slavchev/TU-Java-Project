package com.example.mymodel.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Връзка OneToMany с Book
    @OneToMany(mappedBy = "publisher")  // mappedBy указва, че връзката се управлява чрез полето "publisher" в Book
    private Set<Book> books;

    // Конструктори, гетъри и сетъри
    public Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
