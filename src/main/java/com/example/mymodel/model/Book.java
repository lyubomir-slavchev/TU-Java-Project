package com.example.mymodel.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Добавяме връзката ManyToMany с Author
    @ManyToMany
    @JoinTable(
            name = "author_book",  // Името на създадената връзка в базата данни
            joinColumns = @JoinColumn(name = "book_id"),  // Колоната в таблицата, която се свързва с Book
            inverseJoinColumns = @JoinColumn(name = "author_id")  // Колоната в таблицата, която се свързва с Author
    )
    private Set<Author> authors;

    // Връзка ManyToOne с Publisher
    @ManyToOne
    @JoinColumn(name = "publisher_id")  // Това ще създаде колоната `publisher_id` в таблицата `Book`
    private Publisher publisher;  // Това поле ще свърже книгите с издателите

    // Конструктор по подразбиране
    public Book() {}

    // Конструктор с име и издател
    public Book(String title, Publisher publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    // Конструктор само с заглавие
    public Book(String title) {
        this.title = title;
    }

    // Гетъри и сетъри
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
