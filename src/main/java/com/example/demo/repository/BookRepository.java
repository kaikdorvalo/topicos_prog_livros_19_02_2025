package com.example.demo.repository;

import com.example.demo.model.Book;
import com.example.demo.model.StatusLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(StatusLivro status);
}
