package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String writer;

    private StatusLivro status;

    public Book() {};

    public Long getId() {
        return id;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    public StatusLivro getStatus() {
        return status;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
