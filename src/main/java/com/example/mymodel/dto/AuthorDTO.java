package com.example.mymodel.dto;

public class AuthorDTO {
    private Long id;
    private String name;

    // Конструктори
    public AuthorDTO() {}

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Гетъри и сетъри
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
}
