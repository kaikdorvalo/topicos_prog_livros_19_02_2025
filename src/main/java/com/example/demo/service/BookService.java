package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> getBookById(Long id) {
        return this.bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            this.bookRepository.deleteById(id);
        } else {
            throw new RuntimeException("Not found book with id " + id);
        }
    }

    public Book updateBook(Book book) {
        Optional<Book> foundBook = this.bookRepository.findById(book.getId());
        if (foundBook.isPresent()) {
            return this.bookRepository.save(book);
        }

        return null;
    }
}
